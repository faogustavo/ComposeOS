package com.composeos.win95.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors
import com.composeos.win95.foundation.win98Border
import com.composeos.win95.generated.resources.Res
import com.composeos.win95.generated.resources.windows
import org.jetbrains.compose.resources.painterResource

data class TaskbarItem(
    val title: String,
    val onClick: () -> Unit,
    val isActive: Boolean = false,
    val icon: (@Composable () -> Unit)? = null,
)

@Composable
fun Taskbar(
    onStartClick: () -> Unit,
    openWindows: List<TaskbarItem>,
    isStartMenuOpen: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(Colors.ButtonFace)
                .win98Border(
                    pressed = false,
                    outerWidth = 1.dp,
                    innerWidth = 0.dp,
                ) // Top border usually white
                .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxHeight(),
            forcePressed = isStartMenuOpen,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Windows Logo placeholder
                Image(
                    painterResource(Res.drawable.windows),
                    "Windows Logo",
                    modifier = Modifier.size(16.dp).padding(end = 4.dp),
                )
                BasicText(
                    text = "Start",
                    style =
                        TextStyle(
                            color = Colors.ButtonText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        ),
                )
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Divider / Quick Launch area
        Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Colors.ButtonShadow))

        Spacer(modifier = Modifier.width(4.dp))

        // Window List
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            openWindows.forEach { window ->
                Button(
                    onClick = window.onClick,
                    modifier = Modifier.width(160.dp).fillMaxHeight(),
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    forcePressed = window.isActive,
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (window.icon != null) {
                            window.icon.invoke()
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        BasicText(
                            text = window.title,
                            style = TextStyle(fontSize = 11.sp),
                            maxLines = 1,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Tray
        Box(
            modifier =
                Modifier
                    .width(60.dp)
                    .fillMaxHeight()
                    .background(Colors.ButtonFace)
                    .win98Border(
                        pressed = true,
                        outerWidth = 1.dp,
                        innerWidth = 0.dp,
                    ), // Sunken
            contentAlignment = Alignment.Center,
        ) { BasicText(text = "12:00 PM", style = TextStyle(fontSize = 11.sp)) }
    }
}
