package com.example.trainapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.example.trainapp.ui.navigation.Destinations
import com.example.trainapp.ui.navigation.pair

@Composable
fun TrainNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val (icons, labels) = pair() // Get the icons and labels from the pair function

    NavigationRail(modifier = modifier) {
        Destinations.values().forEachIndexed { index, navItem ->
            // Make sure that the index doesn't go out of bounds for icons and labels
            if (index >= icons.size || index >= labels.size) return@forEachIndexed

            val icon = icons[index]
            val label = labels[index]

            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        painter = icon,
                        contentDescription = label,
                    )
                },
            )
        }
    }
}
