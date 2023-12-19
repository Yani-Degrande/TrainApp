package com.example.trainapp.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBar(
    items: List<String>,
    icons: List<Painter>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = icons[index],
                        contentDescription = item,
                        modifier = Modifier.size(24.dp) // Set the size of the Icon
                    )
                },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                ),
            )
        }
    }
}
