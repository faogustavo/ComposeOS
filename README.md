This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).

## Project Purpose

The project implements a Windows 95‑style desktop environment using Compose Multiplatform, showcasing cross‑platform UI capabilities and providing a nostalgic user experience.

## Tools Used

- **Gemini AI** – Provides AI assistance for code generation, documentation, and project guidance.
- **Antigravity** – The AI coding assistant integrated into the development workflow.


* [/composeApp](./composeApp/src) – Core shared code for the Compose Multiplatform application.
  * `commonMain` – Code shared across all targets.
  * Platform‑specific source sets (`androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain`) contain code compiled only for the respective platform.

* [:win-95](./win-95) – UI library that provides Windows 95‑themed components and assets.
  * Targets Android, iOS, JVM (Desktop), JS, and WasmJs.
  * Includes icons, colors, and classic window decorations.

## Technologies Used

* **Kotlin Multiplatform** – Share business logic and UI code across Android, iOS, Web, and Desktop.
* **Compose Multiplatform** – Declarative UI framework that runs on all supported platforms.
* **Gradle Kotlin DSL** – Build automation and dependency management.
* **ktlint** – Code formatting.

## Development Conventions

* **Code Structure** – Shared code lives in `composeApp/src/commonMain/kotlin`. Platform‑specific code resides in the corresponding source‑set directories.
* **UI** – Built with Compose Multiplatform composables.
* **Build System** – Gradle is used for all targets.
* **Formatting** – Run `ktlint -F` to format the code (ktlint is already installed).

## UI/UX Behavior

Desktop icons mimic the classic Windows 95 interaction model:

* **Single‑click** – Selects the icon (navy background with white dotted border).
* **Double‑click** – Opens the associated application/window.

Implemented with `DesktopIcon` (using `combinedClickable`) and `DesktopState.selectedIcon`.

## Further Exploration

* **Main entry point** – `composeApp/src/commonMain/kotlin/co/compose/dev/os/App.kt` calls `Desktop()` from the `:win-95` module.
* **Resources** – General assets are in `composeApp/src/commonMain/composeResources/drawable/`; Windows 95‑themed assets are in `win-95/src/commonMain/composeResources/drawable/`.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html), [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform), and [Kotlin/Wasm](https://kotl.in/wasm/).
