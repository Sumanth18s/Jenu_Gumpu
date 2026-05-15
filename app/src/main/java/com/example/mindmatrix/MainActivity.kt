package com.example.mindmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mindmatrix.ui.*
import com.example.mindmatrix.ui.screens.*
import com.example.mindmatrix.ui.theme.MindMatrixTheme
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.mindmatrix.MindMatrixApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentLanguage by remember { mutableStateOf("en") }
            val strings = if (currentLanguage == "kn") KannadaStrings else EnglishStrings
            val context = LocalContext.current
            val app = context.applicationContext as MindMatrixApp
            val userDao = app.database.userDao()
            val sharedPrefs = remember { context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE) }
            val isLoggedIn = remember { mutableStateOf(sharedPrefs.getBoolean("is_logged_in", false)) }

            CompositionLocalProvider(LocalStrings provides strings) {
                MindMatrixTheme {
                    AppNavigation(
                        userDao = userDao,
                        isLoggedIn = isLoggedIn.value,
                        onLoginSuccess = {
                            sharedPrefs.edit().putBoolean("is_logged_in", true).apply()
                            isLoggedIn.value = true
                        },
                        onLanguageChanged = { currentLanguage = it }
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    userDao: com.example.mindmatrix.data.local.dao.UserDao,
    isLoggedIn: Boolean,
    onLoginSuccess: () -> Unit,
    onLanguageChanged: (String) -> Unit
) {
    val navController = rememberNavController()
    val startDest = Screen.Login.route
    
    NavHost(navController = navController, startDestination = startDest) {
        composable(Screen.Login.route) {
            LoginScreen(
                userDao = userDao,
                onLoginSuccess = {
                    onLoginSuccess()
                    navController.navigate(Screen.LanguageSelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                userDao = userDao,
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.LanguageSelection.route) {
            LanguageSelectionScreen(onLanguageSelected = { lang ->
                onLanguageChanged(lang)
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.LanguageSelection.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToAddHarvest = { navController.navigate(Screen.AddHarvest.route) },
                onNavigateToHistory = { navController.navigate(Screen.History.route) },
                onNavigateToGrading = { navController.navigate(Screen.GradingGuide.route) },
                onNavigateToCalculator = { navController.navigate(Screen.ProfitCalculator.route) },
                onNavigateToSustainable = { navController.navigate(Screen.SustainableHarvest.route) },
                onNavigateToPriceMonitor = { navController.navigate(Screen.MarketPriceMonitor.route) },
                onNavigateToCharts = { navController.navigate(Screen.Charts.route) },
                onLanguageChanged = onLanguageChanged
            )
        }
        composable(Screen.AddHarvest.route) {
            AddHarvestScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.History.route) {
            HistoryScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.ProfitCalculator.route) {
            ProfitCalculatorScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.GradingGuide.route) {
            GradingGuideScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.SustainableHarvest.route) {
            SustainableHarvestScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.MarketPriceMonitor.route) {
            MarketPriceMonitorScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Charts.route) {
            ChartsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}