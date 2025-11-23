package com.composeos.win95.apps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.components.TextButton
import com.composeos.win95.foundation.Colors
import com.composeos.win95.state.DesktopState
import com.composeos.win95.state.WindowState

@Composable
fun TaskManager(state: DesktopState) {
    var selectedWindow by remember { mutableStateOf<WindowState?>(null) }

    // Filter out the Task Manager window itself from the list
    val displayedWindows =
            remember(state.windows) {
                state.windows.filter { it.title != "Close Program" && it.title != "Task Manager" }
            }

    Column(
            modifier = Modifier.fillMaxSize().background(Colors.ButtonFace).padding(8.dp),
    ) {
        // List of running programs
        Box(
                modifier =
                        Modifier.weight(1f)
                                .fillMaxWidth()
                                .border(1.dp, Colors.ButtonShadow)
                                .background(Colors.White)
                                .padding(4.dp),
        ) {
            LazyColumn {
                items(displayedWindows) { window ->
                    val isSelected = selectedWindow == window
                    Box(
                            modifier =
                                    Modifier.fillMaxWidth()
                                            .background(
                                                    if (isSelected) Colors.Highlight
                                                    else Colors.White,
                                            )
                                            .clickable { selectedWindow = window }
                                            .padding(horizontal = 4.dp, vertical = 2.dp),
                    ) {
                        BasicText(
                                text = window.title,
                                style =
                                        TextStyle(
                                                color =
                                                        if (isSelected) Colors.HighlightText
                                                        else Colors.Black,
                                                fontSize = 12.sp,
                                        ),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Warning message
        BasicText(
                text =
                        "WARNING: Pressing CTRL+ALT+DEL again will restart your computer. You will lose unsaved information in all programs that are running.",
                style =
                        TextStyle(
                                color = Colors.Black,
                                fontSize = 11.sp,
                        ),
                modifier = Modifier.padding(horizontal = 4.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Buttons
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            TextButton(
                    text = "End Task",
                    onClick = {
                        selectedWindow?.let { state.closeWindow(it) }
                        selectedWindow = null
                    },
                    modifier = Modifier.width(100.dp).height(24.dp),
                    enabled = selectedWindow != null,
            )
            TextButton(
                    text = "Shut Down",
                    onClick = {
                        // Close all windows
                        state.windows.toList().forEach { state.closeWindow(it) }
                    },
                    modifier = Modifier.width(100.dp).height(24.dp),
            )
            TextButton(
                    text = "Cancel",
                    onClick = {
                        val taskManagerWindow =
                                state.windows.find {
                                    it.title == "Close Program" || it.title == "Task Manager"
                                }
                        if (taskManagerWindow != null) {
                            state.closeWindow(taskManagerWindow)
                        }
                    },
                    modifier = Modifier.width(100.dp).height(24.dp),
            )
        }
    }
}
