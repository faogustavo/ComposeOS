package com.composeos.win95.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composeos.win95.components.DesktopIcon
import com.composeos.win95.foundation.Colors
import com.composeos.win95.generated.resources.Res
import com.composeos.win95.generated.resources.folder
import org.jetbrains.compose.resources.painterResource

@Composable
fun Explorer() {
    Box(modifier = Modifier.fillMaxSize().background(Colors.White).padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(10) { index ->
                DesktopIcon(
                    title = "Folder $index",
                    onClick = {},
                    textColor = Colors.Black,
                    selected = false,
                    icon = {
                        Image(
                            painterResource(Res.drawable.folder),
                            "Folder",
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }
        }
    }
}
