package com.composeos.win95.components

// import androidx.compose.foundation.clickable // removed; using pointerInput for tap handling
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DesktopIcon(
        title: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        selected: Boolean = false,
        textColor: Color = Colors.White,
        onDoubleClick: (() -> Unit)? = null,
        icon: @Composable () -> Unit = { DefaultIconPlaceholder() },
) {
        Column(
                modifier =
                        modifier.width(70.dp).pointerInput(onClick, onDoubleClick) {
                                detectTapGestures(
                                        onTap = { onClick() },
                                        onDoubleTap = { onDoubleClick?.invoke() }
                                )
                        },
                horizontalAlignment = Alignment.CenterHorizontally,
        ) {
                icon()
                Spacer(modifier = Modifier.height(4.dp))
                BasicText(
                        text = title,
                        style =
                                TextStyle(
                                        color = if (selected) Colors.White else textColor,
                                        fontSize = 11.sp,
                                        textAlign = TextAlign.Center,
                                ),
                        modifier =
                                Modifier.background(
                                                if (selected) Colors.Navy else Color.Transparent,
                                        )
                                        .drawBehind {
                                                if (selected) {
                                                        // Draw dotted border
                                                        val strokeWidth = 1.dp.toPx()
                                                        val pathEffect =
                                                                PathEffect.dashPathEffect(
                                                                        floatArrayOf(1f, 1f),
                                                                        0f
                                                                )

                                                        drawRect(
                                                                color = Colors.White,
                                                                topLeft = Offset(0f, 0f),
                                                                size =
                                                                        Size(
                                                                                size.width,
                                                                                size.height
                                                                        ),
                                                                style =
                                                                        Stroke(
                                                                                width = strokeWidth,
                                                                                pathEffect =
                                                                                        pathEffect,
                                                                        ),
                                                        )
                                                }
                                        },
                )
        }
}

@Composable
fun DefaultIconPlaceholder() {
        Box(
                modifier =
                        Modifier.size(32.dp).background(Colors.Silver).drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                drawRect(
                                        color = Colors.Black,
                                        topLeft = Offset(0f, 0f),
                                        size = Size(size.width, size.height),
                                        style = Stroke(width = strokeWidth),
                                )
                        },
        )
}
