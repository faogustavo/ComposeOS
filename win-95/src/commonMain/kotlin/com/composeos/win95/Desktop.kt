package com.composeos.win95

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
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
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun Desktop() {
    var showStartMenu by remember { mutableStateOf(false) }
    var showMyComputerWindow by remember { mutableStateOf(true) }
    var isMyComputerWindowMinimized by remember { mutableStateOf(false) }
    var isMyComputerWindowMaximized by remember { mutableStateOf(false) }
    var myComputerPosition by remember { mutableStateOf(Offset(100f, 50f)) }

    Column(modifier = Modifier.fillMaxSize().background(Colors.Background)) {
        // Desktop Area (Icons + Windows)
        Box(modifier = Modifier.weight(1f).fillMaxSize()) {
            // Icons
            Column(modifier = Modifier.padding(8.dp)) {
                DesktopIcon(
                    title = "My Computer",
                    onClick = {
                        showMyComputerWindow = true
                        isMyComputerWindowMinimized = false
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
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.internet),
                            "Internet Explorer",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }

            // Windows
            if (showMyComputerWindow && !isMyComputerWindowMinimized) {
                Window(
                    title = "My Computer",
                    icon = {
                        Image(
                            painterResource(Res.drawable.my_computer),
                            "My Computer",
                            modifier = Modifier.size(16.dp),
                        )
                    },
                    onClose = { showMyComputerWindow = false },
                    onMinimize = { isMyComputerWindowMinimized = true },
                    onMaximize = {
                        isMyComputerWindowMaximized =
                            !isMyComputerWindowMaximized
                    },
                    isMaximized = isMyComputerWindowMaximized,
                    modifier =
                        if (isMyComputerWindowMaximized) {
                            Modifier.fillMaxSize()
                        } else {
                            Modifier
                                .offset {
                                    IntOffset(
                                        myComputerPosition.x
                                            .roundToInt(),
                                        myComputerPosition.y
                                            .roundToInt(),
                                    )
                                }.size(300.dp, 200.dp)
                        },
                    onDrag = { delta ->
                        if (!isMyComputerWindowMaximized) {
                            myComputerPosition += delta
                        }
                    },
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(Colors.White)
                                .padding(8.dp),
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
                                            Res.drawable
                                                .disk_drive,
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
                                            Res.drawable
                                                .cd_drive,
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
            }
        }

        // Start Menu
        if (showStartMenu) {
            val density = LocalDensity.current
            val taskbarHeightPx = with(density) { 28.dp.roundToPx() }

            Popup(
                alignment = Alignment.BottomStart,
                offset = IntOffset(0, -taskbarHeightPx),
                onDismissRequest = { showStartMenu = false },
                properties = PopupProperties(focusable = true),
            ) { StartMenu() }
        }

        // Taskbar
        val openWindows =
            remember(showMyComputerWindow, isMyComputerWindowMinimized) {
                val list = mutableListOf<TaskbarItem>()
                if (showMyComputerWindow) {
                    list.add(
                        TaskbarItem(
                            title = "My Computer",
                            onClick = {
                                isMyComputerWindowMinimized =
                                    !isMyComputerWindowMinimized
                            },
                            isActive = !isMyComputerWindowMinimized,
                            icon = {
                                Image(
                                    painterResource(
                                        Res.drawable
                                            .my_computer,
                                    ),
                                    "My Computer",
                                    modifier =
                                        Modifier.size(
                                            16.dp,
                                        ),
                                )
                            },
                        ),
                    )
                }
                list
            }

        Taskbar(
            onStartClick = { showStartMenu = !showStartMenu },
            openWindows = openWindows,
            isStartMenuOpen = showStartMenu,
        )
    }
}
