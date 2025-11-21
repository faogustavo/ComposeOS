package com.composeos.win95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors

@Composable
fun DesktopIcon(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    textColor: Color = _root_ide_package_.com.composeos.win95.foundation.Colors.White,
    icon: @Composable () -> Unit = {
        _root_ide_package_.com.composeos.win95.components
            .DefaultIconPlaceholder()
    },
) {
    Column(
        modifier = modifier.width(70.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Spacer(modifier = Modifier.height(4.dp))
        BasicText(
            text = title,
            style =
                TextStyle(
                    color = if (selected) _root_ide_package_.com.composeos.win95.foundation.Colors.White else textColor,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                ),
            modifier =
                Modifier
                    .background(
                        if (selected) _root_ide_package_.com.composeos.win95.foundation.Colors.Navy else Color.Transparent,
                    ).border(
                        width = 1.dp,
                        color =
                            if (selected) {
                                _root_ide_package_.com.composeos.win95.foundation.Colors.Yellow
                            } else {
                                Color.Transparent
                            },
                        shape =
                            androidx.compose.foundation.shape
                                .RoundedCornerShape(
                                    0.dp,
                                ), // Dotted line ideally
                    ),
        )
    }
}

@Composable
fun DefaultIconPlaceholder() {
    Box(
        modifier =
            Modifier
                .size(
                    32.dp,
                ).background(
                    _root_ide_package_.com.composeos.win95.foundation.Colors.Silver,
                ).border(1.dp, _root_ide_package_.com.composeos.win95.foundation.Colors.Black),
    )
}
