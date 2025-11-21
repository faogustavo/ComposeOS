# Project Overview

This is a Kotlin Multiplatform project designed to target multiple platforms: Android, iOS, Web (Wasm and JS), and Desktop (JVM). The project utilizes Compose Multiplatform for UI development, enabling a shared codebase across different platforms.

The project is structured into two main modules:
- `:composeApp`: Contains the core shared code and platform-specific implementations for Android, iOS, Web, and Desktop.
- `:win-98`: (Further investigation needed to confirm its exact purpose, but likely another module within the multiplatform setup).

## Technologies Used

*   **Kotlin Multiplatform:** For sharing code across various platforms.
*   **Compose Multiplatform:** For building declarative UIs that run on Android, iOS, Desktop, and Web.
*   **Gradle Kotlin DSL:** For build automation and dependency management.

## Building and Running

This project uses Gradle. The following commands can be used to build and run the application on different targets.

### Android

To build and run the Android application:

```shell
./gradlew :composeApp:assembleDebug
```

### Desktop (JVM)

To build and run the Desktop (JVM) application:

```shell
./gradlew :composeApp:run
```

### Web

To build and run the Web application:

*   **Wasm target (faster, modern browsers):**
    ```shell
    ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
    ```
*   **JS target (slower, supports older browsers):**
    ```shell
    ./gradlew :composeApp:jsBrowserDevelopmentRun
    ```

### iOS

To build and run the iOS application:

Open the `iosApp` directory in Xcode and run it from there.

## Development Conventions

*   **Code Structure:** Shared code resides in `composeApp/src/commonMain/kotlin`. Platform-specific code is located in corresponding `androidMain`, `iosMain`, `jsMain`, `jvmMain`, and `wasmJsMain` directories.
*   **UI:** Developed using Compose Multiplatform.
*   **Build System:** Gradle is used for building the project.
*   **Code Formatting:** The project uses `ktlint` for code formatting. Run `ktlint -F` to format the code.

## Further Exploration

*   **`:win-98` module:** This module is a Compose Multiplatform UI library that provides Windows 98-themed components and assets. It targets Android, iOS, JVM (Desktop), JS, and WasmJs.
*   **Main Entry Point:** The main entry point of the Compose Multiplatform application is `composeApp/src/commonMain/kotlin/co/compose/dev/os/App.kt`, which calls the `Desktop()` composable from the `:win-98` module.
*   **Resource Management:**
    *   `composeApp/src/commonMain/composeResources/drawable/` contains general Compose Multiplatform assets (e.g., `compose-multiplatform.xml`).
    *   `win-98/src/commonMain/composeResources/drawable/` contains Windows 98-themed icons (e.g., `cd_drive.png`, `folder.png`, `my_computer.png`).
