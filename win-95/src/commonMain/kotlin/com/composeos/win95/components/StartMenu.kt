package com.composeos.win95.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors
import com.composeos.win95.foundation.win98Border
import com.composeos.win95.generated.resources.*
import com.composeos.win95.generated.resources.find
import com.composeos.win95.generated.resources.folder
import com.composeos.win95.generated.resources.help
import com.composeos.win95.generated.resources.programs
import com.composeos.win95.generated.resources.run
import com.composeos.win95.generated.resources.settings
import com.composeos.win95.generated.resources.shutdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun StartMenu(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(200.dp)
                .height(IntrinsicSize.Min)
                .background(com.composeos.win95.foundation.Colors.ButtonFace)
                .win98Border(pressed = false, outerWidth = 2.dp, innerWidth = 1.dp)
                .padding(2.dp),
    ) {
        // Side Strip
        Box(
            modifier =
                Modifier
                    .width(24.dp)
                    .fillMaxHeight()
                    .background(com.composeos.win95.foundation.Colors.Navy),
            contentAlignment = Alignment.BottomCenter,
        ) {
            BasicText(
                text = "Windows 98",
                style =
                    TextStyle(
                        color = Colors.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    ),
                modifier =
                    Modifier
                        .padding(bottom = 8.dp)
                        .graphicsLayer { rotationZ = -90f }
                        .layout { measurable, constraints ->
                            val placeable =
                                measurable.measure(
                                    constraints.copy(
                                        minWidth = 0,
                                        maxWidth = Constraints.Infinity,
                                    ),
                                )
                            layout(placeable.height, placeable.width) {
                                placeable.place(
                                    x =
                                        -(placeable.width / 2) +
                                            (placeable.height / 2),
                                    y =
                                        -(placeable.height / 2) +
                                            (placeable.width / 2),
                                )
                            }
                        },
            )
        }

        // Menu Items
        Column(modifier = Modifier.weight(1f).padding(start = 2.dp)) {
            com.composeos.win95.components.StartMenuItem(
                text = "Programs",
                icon = com.composeos.win95.generated.resources.Res.drawable.programs,
            )
            com.composeos.win95.components.StartMenuItem(
                text = "Documents",
                icon = com.composeos.win95.generated.resources.Res.drawable.folder,
            )
            com.composeos.win95.components.StartMenuItem(
                text = "Settings",
                icon = com.composeos.win95.generated.resources.Res.drawable.settings,
            )
            com.composeos.win95.components.StartMenuItem(
                text = "Find",
                icon = com.composeos.win95.generated.resources.Res.drawable.find,
            )
            com.composeos.win95.components.StartMenuItem(
                text = "Help",
                icon = com.composeos.win95.generated.resources.Res.drawable.help,
            )
            com.composeos.win95.components.StartMenuItem(
                text = "Run...",
                icon = com.composeos.win95.generated.resources.Res.drawable.run,
            )

            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(com.composeos.win95.foundation.Colors.ButtonShadow)
                        .win98Border(pressed = true),
            )
            Spacer(modifier = Modifier.height(4.dp))

            com.composeos.win95.components.StartMenuItem(
                text = "Shut Down...",
                icon = com.composeos.win95.generated.resources.Res.drawable.shutdown,
            )
        }
    }
}

@Composable
fun StartMenuItem(
    text: String,
    icon: org.jetbrains.compose.resources.DrawableResource,
    onClick: () -> Unit = {},
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painterResource(icon), null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        BasicText(
            text = text,
            style =
                TextStyle(
                    color = com.composeos.win95.foundation.Colors.ButtonText,
                    fontSize = 12.sp,
                ),
        )
    }
}
