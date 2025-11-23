package com.composeos.win95.apps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.components.TextButton
import com.composeos.win95.foundation.Colors
import com.composeos.win95.state.DesktopState

@Composable
fun TaskManager(state: DesktopState) {
    Column(modifier = Modifier.fillMaxSize().background(Colors.ButtonFace).padding(8.dp)) {
        LazyColumn(modifier = Modifier.weight(1f).background(Colors.White)) {
            items(state.windows) { window ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicText(
                        text = window.title,
                        style = TextStyle(color = Colors.Black, fontSize = 14.sp),
                    )
                    TextButton(
                        text = "End Task",
                        onClick = { state.closeWindow(window) },
                        modifier = Modifier.height(24.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(
                text = "Close",
                onClick = {
                    val taskManagerWindow = state.windows.find { it.title == "Task Manager" }
                    if (taskManagerWindow != null) {
                        state.closeWindow(taskManagerWindow)
                    }
                },
                modifier = Modifier.width(100.dp),
            )
        }
    }
}
