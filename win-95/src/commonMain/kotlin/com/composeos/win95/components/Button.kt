package com.composeos.win95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors
import com.composeos.win95.foundation.win98Border

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
    forcePressed: Boolean = false,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val actuallyPressed = isPressed || forcePressed

    Box(
        modifier =
            modifier
                .background(
                    _root_ide_package_.com.composeos.win95.foundation.Colors
                        .ButtonFace,
                ).win98Border(pressed = actuallyPressed)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                ).padding(contentPadding)
                .padding(
                    if (isPressed) {
                        PaddingValues(start = 1.dp, top = 1.dp)
                    } else {
                        PaddingValues(0.dp)
                    },
                ),
        contentAlignment = contentAlignment,
    ) { content() }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    _root_ide_package_.com.composeos.win95.components.Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        BasicText(
            text = text,
            style =
                TextStyle(
                    color =
                        _root_ide_package_.com.composeos.win95.foundation
                            .Colors.ButtonText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
    }
}
