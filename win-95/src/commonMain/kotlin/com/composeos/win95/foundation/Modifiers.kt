package com.composeos.win95.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.win98Border(
    pressed: Boolean = false,
    outerWidth: Dp = 1.dp,
    innerWidth: Dp = 1.dp,
): Modifier =
    composed {
        val light = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonHighlight
        val shadow = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonShadow
        val darkShadow = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonDkShadow
        val face = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonFace

        drawBehind {
            val w = size.width
            val h = size.height
            val ow = outerWidth.toPx()
            val iw = innerWidth.toPx()

            if (pressed) {
                // Outer Top/Left (Black)
                drawLine(darkShadow, Offset(0f, 0f), Offset(w, 0f), ow)
                drawLine(darkShadow, Offset(0f, 0f), Offset(0f, h), ow)

                // Outer Bottom/Right (White)
                drawLine(light, Offset(w, 0f), Offset(w, h), ow)
                drawLine(light, Offset(0f, h), Offset(w, h), ow)

                // Inner Top/Left (Shadow)
                drawLine(shadow, Offset(ow, ow), Offset(w - ow, ow), iw)
                drawLine(shadow, Offset(ow, ow), Offset(ow, h - ow), iw)
            } else {
                // Outer Top/Left (White)
                drawLine(light, Offset(0f, 0f), Offset(w - 1, 0f), ow)
                drawLine(light, Offset(0f, 0f), Offset(0f, h - 1), ow)

                // Outer Bottom/Right (Black)
                drawLine(darkShadow, Offset(w, 0f), Offset(w, h), ow)
                drawLine(darkShadow, Offset(0f, h), Offset(w, h), ow)

                // Inner Bottom/Right (Shadow)
                drawLine(shadow, Offset(w - ow, ow), Offset(w - ow, h - ow), iw)
                drawLine(shadow, Offset(ow, h - ow), Offset(w - ow, h - ow), iw)
            }
        }
    }
