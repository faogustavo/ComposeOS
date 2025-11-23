package com.composeos.win95.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeos.win95.components.Button
import com.composeos.win95.components.DesktopIcon
import com.composeos.win95.foundation.Colors
import com.composeos.win95.generated.resources.Res
import com.composeos.win95.generated.resources.folder
import com.composeos.win95.generated.resources.my_computer
import com.composeos.win95.generated.resources.windows
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class FileItem(
    val name: String,
    val isFolder: Boolean,
    val icon: DrawableResource,
)

@Composable
fun FileExplorer(initialPath: String = "/desktop/Themes/") {
    var currentPath by remember { mutableStateOf(initialPath) }
    var selectedItem by remember { mutableStateOf<FileItem?>(null) }

    // Sample file structure
    val fileItems =
        remember {
            listOf(
                FileItem("TEST", true, Res.drawable.folder),
                FileItem("Windows Official", true, Res.drawable.folder),
                FileItem("classicthemes8", false, Res.drawable.windows),
                FileItem("Ash.themepack", false, Res.drawable.windows),
                FileItem("Blue.themepack", false, Res.drawable.windows),
                FileItem("Fx.themepack", false, Res.drawable.windows),
                FileItem("loney.themepac", false, Res.drawable.windows),
                FileItem("Peggys", false, Res.drawable.windows),
                FileItem("verShell.themepe", false, Res.drawable.windows),
                FileItem("cision.themep", false, Res.drawable.windows),
                FileItem("Red", false, Res.drawable.windows),
                FileItem("Solarized", false, Res.drawable.windows),
                FileItem("Vine.themepack", false, Res.drawable.windows),
                FileItem("Dark.themepack", false, Res.drawable.windows),
                FileItem("Dark.themepack", false, Res.drawable.windows),
                FileItem("Windows 1.themepack", false, Res.drawable.windows),
                FileItem("Windows Hybrid -", false, Res.drawable.windows),
            )
        }

    Column(modifier = Modifier.fillMaxSize().background(Colors.ButtonFace)) {
        // Menu Bar
        MenuBar()

        // Toolbar
        Toolbar()

        // Address Bar
        AddressBar(currentPath)

        // Main Content Area
        Row(modifier = Modifier.weight(1f)) {
            // Left Sidebar
            Sidebar(selectedItem)

            // File Grid
            FileGrid(fileItems, selectedItem) { item -> selectedItem = item }
        }

        // Status Bar
        StatusBar(fileItems.size)
    }
}

@Composable
private fun MenuBar() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Colors.ButtonFace)
                .border(1.dp, Colors.BorderDark),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val menuItems = listOf("File", "Edit", "View", "Go", "Favorites", "Tools", "Help")
        menuItems.forEach { item ->
            BasicText(
                text = item,
                style = TextStyle(fontSize = 11.sp, color = Colors.Black),
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
    }
}

@Composable
private fun Toolbar() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Colors.ButtonFace)
                .border(1.dp, Colors.BorderDark)
                .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        // Navigation buttons
        ToolbarButton("Back")
        ToolbarButton("Forward")
        ToolbarButton("Up")

        Spacer(modifier = Modifier.width(4.dp))

        // Edit buttons
        ToolbarButton("Cut")
        ToolbarButton("Copy")
        ToolbarButton("Paste")

        Spacer(modifier = Modifier.width(4.dp))

        // Action buttons
        ToolbarButton("Undo")
        ToolbarButton("Delete")
        ToolbarButton("Properties")
        ToolbarButton("Views")
    }
}

@Composable
private fun ToolbarButton(label: String) {
    Button(
        onClick = {},
        modifier = Modifier.height(28.dp).padding(horizontal = 2.dp),
    ) {
        BasicText(
            text = label,
            style = TextStyle(fontSize = 10.sp, color = Colors.Black),
            modifier = Modifier.padding(horizontal = 4.dp),
        )
    }
}

@Composable
private fun AddressBar(path: String) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(Colors.ButtonFace)
                .border(1.dp, Colors.BorderDark)
                .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicText(
            text = "Address",
            style = TextStyle(fontSize = 11.sp, color = Colors.Black),
            modifier = Modifier.padding(end = 4.dp),
        )

        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Colors.White)
                    .border(1.dp, Colors.BorderDark)
                    .padding(horizontal = 4.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(Res.drawable.folder),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                BasicText(
                    text = path,
                    style = TextStyle(fontSize = 11.sp, color = Colors.Black),
                )
            }
        }

        // Dropdown arrow
        Box(
            modifier =
                Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .background(Colors.ButtonFace)
                    .border(1.dp, Colors.BorderDark),
        )
    }
}

@Composable
private fun RowScope.Sidebar(selectedItem: FileItem?) {
    Column(
        modifier =
            Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(Colors.ButtonFace)
                .border(1.dp, Colors.BorderDark)
                .padding(8.dp),
    ) {
        // Folder icon and title
        Image(
            painterResource(Res.drawable.folder),
            contentDescription = null,
            modifier = Modifier.size(48.dp).align(Alignment.Start),
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicText(
            text = "Themes",
            style =
                TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Colors.Black,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        BasicText(
            text = "Select an item to view its description.",
            style = TextStyle(fontSize = 11.sp, color = Colors.Black),
        )
    }
}

@Composable
private fun RowScope.FileGrid(
    items: List<FileItem>,
    selectedItem: FileItem?,
    onItemClick: (FileItem) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Colors.White)
                .border(1.dp, Colors.BorderDark)
                .padding(8.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items) { item ->
                DesktopIcon(
                    title = item.name,
                    onClick = { onItemClick(item) },
                    textColor = Colors.Black,
                    selected = selectedItem == item,
                    icon = {
                        Image(
                            painterResource(item.icon),
                            contentDescription = item.name,
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun StatusBar(itemCount: Int) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Colors.ButtonFace)
                .border(1.dp, Colors.BorderDark)
                .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicText(
            text = "$itemCount object(s)",
            style = TextStyle(fontSize = 11.sp, color = Colors.Black),
        )

        Spacer(modifier = Modifier.weight(1f))

        // Computer icon and name
        Image(
            painterResource(Res.drawable.my_computer),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        BasicText(
            text = "My Computer",
            style = TextStyle(fontSize = 11.sp, color = Colors.Black),
        )
    }
}
