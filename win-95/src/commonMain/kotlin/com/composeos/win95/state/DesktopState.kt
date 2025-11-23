package com.composeos.win95.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

sealed class ApplicationType {
    data object MyComputer : ApplicationType()

    data object RecycleBin : ApplicationType()

    data object Explorer : ApplicationType()

    data object FileExplorer : ApplicationType()

    data object TaskManager : ApplicationType()
}

class WindowState(
    val id: String,
    val type: ApplicationType,
    val title: String,
    val icon: DrawableResource,
    initialPosition: Offset = Offset(50f, 50f),
    initialSize: DpSize = DpSize(400.dp, 300.dp),
) {
    var isOpen by mutableStateOf(true)
    var isMinimized by mutableStateOf(false)
    var isMaximized by mutableStateOf(false)
    var position by mutableStateOf(initialPosition)
    var size by mutableStateOf(initialSize)
}

class DesktopState {
    val windows = mutableStateListOf<WindowState>()
    var startMenuOpen by mutableStateOf(false)

    fun openWindow(
        type: ApplicationType,
        title: String,
        icon: DrawableResource,
    ) {
        val newWindow =
            WindowState(
                id = generateId(),
                type = type,
                title = title,
                icon = icon,
                initialPosition =
                    Offset(50f + (windows.size * 20), 50f + (windows.size * 20)),
            )
        windows.add(newWindow)
        bringToFront(newWindow)
    }

    fun closeWindow(window: WindowState) {
        windows.remove(window)
    }

    fun minimizeWindow(window: WindowState) {
        window.isMinimized = true
    }

    fun restoreWindow(window: WindowState) {
        window.isMinimized = false
        bringToFront(window)
    }

    fun maximizeWindow(window: WindowState) {
        window.isMaximized = !window.isMaximized
        bringToFront(window)
    }

    fun bringToFront(window: WindowState) {
        if (windows.remove(window)) {
            windows.add(window)
        }
    }

    private fun generateId(): String = "win_${(0..1000000).random()}"
}
