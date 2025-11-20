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
import com.composeos.win95.components.Window
import com.composeos.win95.foundation.Colors
import com.composeos.win95.generated.resources.cd_drive
import com.composeos.win95.generated.resources.disk_drive
import com.composeos.win95.generated.resources.inbox
import com.composeos.win95.generated.resources.internet
import com.composeos.win95.generated.resources.my_computer
import com.composeos.win95.generated.resources.network
import com.composeos.win95.generated.resources.recycle_bin
import com.composeos.win95.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun Desktop() {
    var showStartMenu by remember { mutableStateOf(false) }
    var showMyComputerWindow by remember { mutableStateOf(true) }
    var myComputerPosition by remember { mutableStateOf(Offset(100f, 50f)) }

    Column(modifier = Modifier.fillMaxSize().background(_root_ide_package_.com.composeos.win95.foundation.Colors.Background)) {
        // Desktop Area (Icons + Windows)
        Box(modifier = Modifier.weight(1f).fillMaxSize()) {
            // Icons
            Column(modifier = Modifier.padding(8.dp)) {
                _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                    title = "My Computer",
                    onClick = { showMyComputerWindow = true },
                    selected = false,
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.my_computer),
                            "My Computer",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                    title = "Recycle Bin",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.recycle_bin),
                            "Recycle Bin",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                    title = "Network Neighborhood",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.network),
                            "Network Neighborhood",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                    title = "Inbox",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.inbox),
                            "Inbox",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
                _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                    title = "Internet Explorer",
                    onClick = {},
                    selected = false,
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.internet),
                            "Internet Explorer",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }

            // Windows
            if (showMyComputerWindow) {
                _root_ide_package_.com.composeos.win95.components.Window(
                    title = "My Computer",
                    icon = {
                        Image(
                            painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.my_computer),
                            "My Computer",
                            modifier = Modifier.size(16.dp),
                        )
                    },
                    onClose = { showMyComputerWindow = false },
                    modifier =
                        Modifier
                            .offset {
                                IntOffset(
                                    myComputerPosition.x.roundToInt(),
                                    myComputerPosition.y.roundToInt(),
                                )
                            }.size(300.dp, 200.dp),
                    onDrag = { delta -> myComputerPosition += delta },
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(_root_ide_package_.com.composeos.win95.foundation.Colors.White).padding(8.dp)
                    ) {
                        Row {
                            _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                                title = "(C:)",
                                onClick = {},
                                textColor = _root_ide_package_.com.composeos.win95.foundation.Colors.Black,
                                selected = false,
                                icon = {
                                    Image(
                                        painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.disk_drive),
                                        "C:",
                                        modifier = Modifier.size(32.dp),
                                    )
                                },
                            )
                            _root_ide_package_.com.composeos.win95.components.DesktopIcon(
                                title = "(D:)",
                                onClick = {},
                                textColor = _root_ide_package_.com.composeos.win95.foundation.Colors.Black,
                                selected = false,
                                icon = {
                                    Image(
                                        painterResource(_root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.cd_drive),
                                        "D:",
                                        modifier = Modifier.size(32.dp),
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
            ) { _root_ide_package_.com.composeos.win95.components.StartMenu() }
        }

        // Taskbar
        _root_ide_package_.com.composeos.win95.components.Taskbar(onStartClick = { showStartMenu = !showStartMenu })
    }
}
