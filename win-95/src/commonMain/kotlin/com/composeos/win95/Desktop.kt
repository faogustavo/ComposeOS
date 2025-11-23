package com.composeos.win95

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.composeos.win95.apps.Explorer
import com.composeos.win95.apps.FileExplorer
import com.composeos.win95.apps.TaskManager
import com.composeos.win95.components.DesktopIcon
import com.composeos.win95.components.StartMenu
import com.composeos.win95.components.Taskbar
import com.composeos.win95.components.TaskbarItem
import com.composeos.win95.components.Window
import com.composeos.win95.foundation.Colors
import com.composeos.win95.generated.resources.*
import com.composeos.win95.generated.resources.cd_drive
import com.composeos.win95.generated.resources.disk_drive
import com.composeos.win95.generated.resources.inbox
import com.composeos.win95.generated.resources.internet
import com.composeos.win95.generated.resources.my_computer
import com.composeos.win95.generated.resources.network
import com.composeos.win95.generated.resources.recycle_bin
import com.composeos.win95.state.ApplicationType
import com.composeos.win95.state.DesktopState
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun Desktop() {
    val state = remember { DesktopState() }

    Column(modifier = Modifier.fillMaxSize().background(Colors.Background)) {
        // Desktop Area (Icons + Windows)
        Box(modifier = Modifier.weight(1f).fillMaxSize()) {
            // Icons
            Column(modifier = Modifier.padding(8.dp)) {
                DesktopIcon(
                    title = "My Computer",
                    onClick = {
                        state.openWindow(
                            ApplicationType.MyComputer,
                            "My Computer",
                            Res.drawable.my_computer,
                        )
                    },
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.my_computer),
                            "My Computer",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Recycle Bin",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.recycle_bin),
                            "Recycle Bin",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Network Neighborhood",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.network),
                            "Network Neighborhood",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Inbox",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.inbox),
                            "Inbox",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Internet Explorer",
                    onClick = {
                        state.openWindow(
                            ApplicationType.Explorer,
                            "Internet Explorer",
                            Res.drawable.internet,
                        )
                    },
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.internet),
                            "Internet Explorer",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Task Manager",
                    onClick = {
                        state.openWindow(
                            ApplicationType.TaskManager,
                            "Close Program",
                            Res.drawable.programs, // Using programs icon as
                            // placeholder
                        )
                    },
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.programs),
                            "Task Manager",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                DesktopIcon(
                    title = "Themes",
                    onClick = {
                        state.openWindow(
                            ApplicationType.FileExplorer,
                            "Themes",
                            Res.drawable.folder,
                        )
                    },
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.folder),
                            "Themes",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }

            // Windows
            state.windows.forEach { window ->
                key(window.id) {
                    if (window.isOpen && !window.isMinimized) {
                        Window(
                            title = window.title,
                            icon = {
                                Image(
                                    painterResource(
                                        window.icon,
                                    ),
                                    window.title,
                                    modifier =
                                        Modifier.size(
                                            16.dp,
                                        ),
                                )
                            },
                            onClose = { state.closeWindow(window) },
                            onMinimize = { state.minimizeWindow(window) },
                            onMaximize = { state.maximizeWindow(window) },
                            isMaximized = window.isMaximized,
                            modifier =
                                if (window.isMaximized) {
                                    Modifier.fillMaxSize()
                                } else {
                                    Modifier
                                        .offset {
                                            IntOffset(
                                                window.position.x.roundToInt(),
                                                window.position.y.roundToInt(),
                                            )
                                        }.size(window.size)
                                },
                            onDrag = { delta ->
                                if (!window.isMaximized) {
                                    window.position += delta
                                }
                            },
                            onClick = { state.bringToFront(window) },
                        ) {
                            when (window.type) {
                                ApplicationType.MyComputer -> {
                                    Box(
                                        modifier =
                                            Modifier
                                                .fillMaxSize()
                                                .background(
                                                    Colors.White,
                                                ).padding(
                                                    8.dp,
                                                ),
                                    ) {
                                        Row {
                                            DesktopIcon(
                                                title = "(C:)",
                                                onClick = {},
                                                textColor = Colors.Black,
                                                selected = false,
                                                icon = {
                                                    Image(
                                                        painterResource(
                                                            Res.drawable.disk_drive,
                                                        ),
                                                        "C:",
                                                        modifier =
                                                            Modifier.size(
                                                                32.dp,
                                                            ),
                                                    )
                                                },
                                            )
                                            DesktopIcon(
                                                title = "(D:)",
                                                onClick = {},
                                                textColor = Colors.Black,
                                                selected = false,
                                                icon = {
                                                    Image(
                                                        painterResource(
                                                            Res.drawable.cd_drive,
                                                        ),
                                                        "D:",
                                                        modifier =
                                                            Modifier.size(
                                                                32.dp,
                                                            ),
                                                    )
                                                },
                                            )
                                        }
                                    }
                                }
                                ApplicationType.Explorer -> Explorer()
                                ApplicationType.FileExplorer -> FileExplorer()
                                ApplicationType.TaskManager -> TaskManager(state)
                                else ->
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(
                                                Colors.White,
                                            ),
                                    )
                            }
                        }
                    }
                }
            }
        }

        // Start Menu
        if (state.startMenuOpen) {
            val density = LocalDensity.current
            val taskbarHeightPx = with(density) { 28.dp.roundToPx() }

            Popup(
                alignment = Alignment.BottomStart,
                offset = IntOffset(0, -taskbarHeightPx),
                onDismissRequest = { state.startMenuOpen = false },
                properties = PopupProperties(focusable = true),
            ) { StartMenu() }
        }

        // Taskbar
        Taskbar(
            onStartClick = { state.startMenuOpen = !state.startMenuOpen },
            openWindows =
                state.windows.map { window ->
                    TaskbarItem(
                        title = window.title,
                        onClick = {
                            if (window.isMinimized) {
                                state.restoreWindow(window)
                            } else if (window == state.windows.lastOrNull()) {
                                state.minimizeWindow(window)
                            } else {
                                state.bringToFront(window)
                            }
                        },
                        isActive =
                            !window.isMinimized &&
                                window == state.windows.lastOrNull(),
                        icon = {
                            Image(
                                painterResource(window.icon),
                                window.title,
                                modifier =
                                    Modifier.size(
                                        16.dp,
                                    ),
                            )
                        },
                    )
                },
            isStartMenuOpen = state.startMenuOpen,
        )
    }
}
