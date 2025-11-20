package com.composeos.win95.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.foundation.Colors
import com.composeos.win95.foundation.win98Border
import com.composeos.win95.generated.resources.find
import com.composeos.win95.generated.resources.folder
import com.composeos.win95.generated.resources.help
import com.composeos.win95.generated.resources.programs
import com.composeos.win95.generated.resources.run
import com.composeos.win95.generated.resources.settings
import com.composeos.win95.generated.resources.shutdown
import com.composeos.win95.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun StartMenu(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(200.dp)
                .background(_root_ide_package_.com.composeos.win95.foundation.Colors.ButtonFace)
                .win98Border(pressed = false, outerWidth = 2.dp, innerWidth = 1.dp)
                .padding(2.dp),
    ) {
        // Side Strip
        Box(
            modifier =
                Modifier
                    .width(24.dp)
                    .fillMaxHeight() // This might need fixed height if parent
                    // not fixed
                    .height(250.dp) // Approximate height
                    .background(_root_ide_package_.com.composeos.win95.foundation.Colors.Navy),
            contentAlignment = Alignment.BottomCenter,
        ) {
            BasicText(
                text = "Windows 98",
                style =
                    TextStyle(
                        color = _root_ide_package_.com.composeos.win95.foundation.Colors.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    ),
                modifier = Modifier.padding(bottom = 8.dp).rotate(-90f),
            )
        }

        // Menu Items
        Column(modifier = Modifier.weight(1f).padding(start = 2.dp)) {
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Programs",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.programs
            )
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Documents",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.folder
            )
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Settings",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.settings
            )
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Find",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.find
            )
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Help",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.help
            )
            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Run...",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.run
            )

            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(_root_ide_package_.com.composeos.win95.foundation.Colors.ButtonShadow)
                        .win98Border(pressed = true),
            )
            Spacer(modifier = Modifier.height(4.dp))

            _root_ide_package_.com.composeos.win95.components.StartMenuItem(
                text = "Shut Down...",
                icon = _root_ide_package_.com.composeos.win95.generated.resources.Res.drawable.shutdown
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
            style = TextStyle(color = _root_ide_package_.com.composeos.win95.foundation.Colors.ButtonText, fontSize = 12.sp),
        )
    }
}
