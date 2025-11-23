# Project Overview

This is a Kotlin Multiplatform project designed to target multiple platforms: Android, iOS, Web (Wasm and JS), and Desktop (JVM). The project utilizes Compose Multiplatform for UI development, enabling a shared codebase across different platforms.

The project is structured into two main modules:
- `:composeApp`: Contains the core shared code and platform-specific implementations for Android, iOS, Web, and Desktop.
- `:win-95`: A Compose Multiplatform UI library that provides Windows 95-themed components and assets. It targets Android, iOS, JVM (Desktop), JS, and WasmJs.

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
    * The `ktlint` is already installed in the path

## UI/UX Behavior

### Desktop Icons

Desktop icons follow the traditional desktop interaction pattern:
- **Single-click**: Selects the icon (highlighted with a navy background and white dotted border)
- **Double-click**: Opens the application/window associated with the icon

This behavior is implemented using:
- `DesktopIcon` component with `onDoubleClick` parameter
- `DesktopState.selectedIcon` to track the currently selected icon
- `combinedClickable` modifier from Compose Foundation
- `drawBehind` modifier with `PathEffect.dashPathEffect` to create the authentic Windows 95 dotted border selection style

## Further Exploration

*   **Main Entry Point:** The main entry point of the Compose Multiplatform application is `composeApp/src/commonMain/kotlin/co/compose/dev/os/App.kt`, which calls the `Desktop()` composable from the `:win-95` module.
*   **Resource Management:**
    *   `composeApp/src/commonMain/composeResources/drawable/` contains general Compose Multiplatform assets (e.g., `compose-multiplatform.xml`).
    *   `win-95/src/commonMain/composeResources/drawable/` contains Windows 95-themed icons (e.g., `cd_drive.png`, `folder.png`, `my_computer.png`).
