# Jenu-Gumpu: How to Run the Project

This project consists of an **Android Application** (frontend) and a **Node.js Express Server** (backend). Follow these steps to get everything running locally.

## 1. Prerequisites
- **Node.js** (v14 or higher)
- **Android Studio** (Hedgehog or later recommended)
- **Android Emulator** or a physical device

---

## 2. Running the Backend Server
The backend handles user authentication, harvest logging, and collective stock calculations.

1.  Open your terminal and navigate to the backend directory:
    ```bash
    cd backend
    ```
2.  Install dependencies (if you haven't already):
    ```bash
    npm install
    ```
3.  Start the server:
    ```bash
    npm start
    ```
    *The server will run on `http://localhost:5000`.*

---

## 3. Running the Android App
The app is built using Jetpack Compose and connects to the backend server.

1.  Open **Android Studio**.
2.  Select **Open** and choose the root `Project` folder.
3.  Wait for Gradle to sync.
4.  **Backend Configuration**:
    - If using an **Android Emulator**, the app is pre-configured to use `http://10.0.2.2:5000/`.
    - If using a **Physical Device**, update the `BASE_URL` in `app/src/main/java/com/example/mindmatrix/network/RetrofitClient.kt` to your computer's local IP address (e.g., `http://192.168.1.5:5000/`).
5.  Select your device/emulator and click the **Run** button (green play icon).

---

## 4. Troubleshooting
- **Connection Refused**: Ensure the backend server is running *before* performing any actions in the app.
- **Emulator Network**: Remember that the Emulator treats `localhost` as its own internal loopback. Use `10.0.2.2` to access the host machine.
- **Kannada Font**: The app uses standard Android fonts. If Kannada text doesn't display correctly on a physical device, ensure the device supports Indic languages.

How to Use the Application
Open the Jenu-Gumpu application on your mobile device.
The app will display the Login Screen.

## 5. New users can register by clicking:

New User? Click Here
Enter the required details:
Name
Email
Password
Click the Register button to create an account.
Existing users can log in using their registered:
Email
Password
After successful login, the Language Selection Screen will appear.
Select your preferred language:
English
Kannada
The application dashboard will open with various features.

## 6. Main Features of the Application

• Add Harvest – Users can add honey harvest details such as quantity, date, and collection information.

• Harvest History – Displays all previously saved harvest records for future reference.

• Grading Guide – Provides honey quality grading information and standards.

• Profit Calculator – Helps users calculate estimated profit based on production and selling price.

• Sustainable Harvest – Displays eco-friendly and safe honey harvesting practices.

• Market Price Monitor – Shows current honey market prices for better selling decisions.

• Charts & Analytics – Provides graphical reports and harvest analysis.

• Multi-Language Support – Users can switch between English and Kannada languages.

• Secure Login System – User authentication is implemented using Room Database for secure local login.

• Offline Functionality – The application stores data locally, allowing core features to work without internet access.
