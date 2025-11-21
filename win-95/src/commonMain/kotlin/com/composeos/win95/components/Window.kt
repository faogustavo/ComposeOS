package com.composeos.win95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors
import com.composeos.win95.foundation.win98Border

@Composable
fun Window(
    title: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    onDrag: ((Offset) -> Unit)? = null,
    onMinimize: (() -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    isActive: Boolean = true,
    content: @Composable () -> Unit,
) {
    Column(
        modifier =
            modifier
                .background(
                    _root_ide_package_.com.composeos.win95.foundation.Colors
                        .ButtonFace,
                ).win98Border(pressed = false, outerWidth = 2.dp, innerWidth = 1.dp)
                .padding(3.dp),
    ) {
        // Title Bar
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        brush =
                            if (isActive) {
                                Brush.horizontalGradient(
                                    colors =
                                        listOf(
                                            _root_ide_package_.com
                                                .composeos.win95
                                                .foundation
                                                .Colors.Navy,
                                            _root_ide_package_.com
                                                .composeos.win95
                                                .foundation
                                                .Colors.Blue,
                                        ), // Simplified
                                    // gradient
                                )
                            } else {
                                Brush.horizontalGradient(
                                    colors =
                                        listOf(
                                            _root_ide_package_.com
                                                .composeos.win95
                                                .foundation
                                                .Colors.Gray,
                                            _root_ide_package_.com
                                                .composeos.win95
                                                .foundation
                                                .Colors.Silver,
                                        ),
                                )
                            },
                    ).padding(horizontal = 2.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            onDrag?.invoke(dragAmount)
                        }
                    },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(2.dp))
            }

            BasicText(
                text = title,
                style =
                    TextStyle(
                        color =
                            _root_ide_package_.com.composeos.win95.foundation.Colors
                                .TitleBarText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                modifier = Modifier.weight(1f),
            )

            if (onMinimize != null) {
                _root_ide_package_.com.composeos.win95.components.Button(
                    onClick = onMinimize,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(16.dp, 14.dp),
                ) {
                    BasicText(
                        text = "_",
                        style =
                            TextStyle(
                                color =
                                    _root_ide_package_.com.composeos.win95
                                        .foundation.Colors.ButtonText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                        modifier = Modifier.offset(y = (-3).dp),
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
            }

            _root_ide_package_.com.composeos.win95.components.Button(
                onClick = onClose,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(16.dp, 14.dp),
            ) {
                BasicText(
                    text = "X",
                    style =
                        TextStyle(
                            color =
                                _root_ide_package_.com.composeos.win95.foundation
                                    .Colors.ButtonText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            }
        }

        // Content
        Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(top = 2.dp)) { content() }
    }
}
