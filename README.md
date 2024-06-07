# MyCards - All Your Bank Cards in One Place

## Overview

**MyCards** is an Android application designed to help users store and manage all their bank card information in one place. This app allows users to view their card information anytime they need it, and it works completely offline, ensuring privacy and security.

## Features

- **Offline Access**: All card information is stored locally on the device, ensuring privacy and accessibility even without an internet connection.
- **Jetpack Compose**: The app is built entirely using Jetpack Compose, offering a modern and efficient UI development experience.
- **Room Database**: Utilized for local storage of card information, ensuring data persistence and efficient querying.
- **Proto DataStore**: For secure and efficient storage of user data, including signup and login information.
- **Biometric Login**: Supports biometric authentication (fingerprint) for quick and secure access without the need for a PIN.
- **Hilt Dependency Injection**: Ensures efficient and scalable dependency injection throughout the app.
- **Jetpack Navigation**: Simplifies and manages in-app navigation.

## Technical Stack

- **Kotlin**: The primary language used for Android development.
- **Jetpack Compose**: For building the UI in a declarative manner.
- **Room Database**: For storing and managing card information locally.
- **Proto DataStore**: For secure storage of user data.
- **BiometricPrompt API**: For handling biometric authentication.
- **Hilt**: For dependency injection.
- **Jetpack Navigation**: For managing in-app navigation.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/karnasurendra/Android-Compose-MVVM-MyCards
    ```

2. Open the project in Android Studio.

3. Build and run the project on your Android device or emulator.

## Usage

1. **Signup**:
    - Launch the app and follow the signup process to create your account.
    - Your data will be securely stored using Proto DataStore.

2. **Login**:
    - You can log in using the PIN you set during signup or use biometric authentication if enabled.

3. **Add and View Cards**:
    - After logging in, you can add your bank cards to the app.
    - Your card information will be stored locally using Room Database.
    - View your stored cards anytime within the app.

## Screenshots

![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.32.jpeg)
![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.33.jpeg)
![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.34%20(1).jpeg)
![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.33%20(1).jpeg)
![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.34%20(2).jpeg)
![My Image](https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Image%202024-06-07%20at%2018.31.34.jpeg)


## Video

<video width="640" height="360" controls>
    <source src="https://github.com/karnasurendra/Android-Compose-MVVM-MyCards/blob/developement/WhatsApp%20Video%202024-06-07%20at%2018.35.27.mp4" type="video/mp4">
    Your browser does not support the video tag.
</video>


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


## Acknowledgements

- **Jetpack Compose**: For providing a modern UI development framework.
- **Room Database**: For efficient local data storage.
- **Proto DataStore**: For secure and efficient data storage.
- **BiometricPrompt API**: For enabling secure biometric authentication.
- **Hilt**: For dependency injection.
- **Jetpack Navigation**: For managing in-app navigation.