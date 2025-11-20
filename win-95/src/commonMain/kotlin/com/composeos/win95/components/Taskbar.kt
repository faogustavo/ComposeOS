package com.composeos.win95.components

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

@Composable
fun Taskbar(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(_root_ide_package_.com.composeos.win95.foundation.Colors.ButtonFace)
                .win98Border(
                    pressed = false,
                    outerWidth = 1.dp,
                    innerWidth = 0.dp,
                ) // Top border usually white
                .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        _root_ide_package_.com.composeos.win95.components.Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Windows Logo placeholder
                Box(
                    modifier =
                        Modifier
                            .padding(end = 4.dp)
                            .background(_root_ide_package_.com.composeos.win95.foundation.Colors.Black)
                            .width(10.dp)
                            .height(10.dp),
                )
                BasicText(
                    text = "Start",
                    style =
                        TextStyle(
                            color = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        ),
                )
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Divider / Quick Launch area
        Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(_root_ide_package_.com.composeos.win95.foundation.Colors.ButtonShadow))

        Spacer(modifier = Modifier.width(4.dp))

        // Window List (Placeholder)
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            // Example active window button
            _root_ide_package_.com.composeos.win95.components.Button(
                onClick = {},
                modifier = Modifier.weight(1f).fillMaxHeight(),
                contentPadding =
                    androidx.compose.foundation.layout
                        .PaddingValues(horizontal = 4.dp),
            ) { BasicText(text = "My Computer", style = TextStyle(fontSize = 11.sp), maxLines = 1) }
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Tray
        Box(
            modifier =
                Modifier
                    .width(60.dp)
                    .fillMaxHeight()
                    .background(_root_ide_package_.com.composeos.win95.foundation.Colors.ButtonFace)
                    .win98Border(
                        pressed = true,
                        outerWidth = 1.dp,
                        innerWidth = 0.dp,
                    ), // Sunken
            contentAlignment = Alignment.Center,
        ) { BasicText(text = "12:00 PM", style = TextStyle(fontSize = 11.sp)) }
    }
}
