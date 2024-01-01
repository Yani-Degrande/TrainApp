package com.example.trainapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavDestination
import com.example.trainapp.R
import com.example.trainapp.ui.navigation.Destinations
import com.example.trainapp.ui.navigation.pair

/**
 * A composable function that creates the content for a navigation drawer.
 * It displays a list of navigation items, each with an icon and a label.
 *
 * This function uses the `ExperimentalMaterial3Api` class, indicating that it relies on an API
 * that is experimental and may change in the future.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed A lambda function that is invoked with the name of the navigation item when it is pressed.
 * @param modifier A [Modifier] applied to the Column layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    val (icons, labels) = pair() // Get the icons and labels from the pair function

    Column(modifier = modifier) {
        Destinations.values().forEachIndexed { index, navItem ->
            // Make sure that the index doesn't go out of bounds for icons and labels
            if (index >= icons.size || index >= labels.size) return@forEachIndexed

            val icon = icons[index]
            val label = labels[index]

            val isSelected = selectedDestination?.route == navItem.name
            NavigationDrawerItem(
                selected = isSelected,
                label = {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header)),
                    )
                },
                icon = {
                    Icon(
                        painter = icon,
                        contentDescription = label,
                    )
                },
                colors =
                    NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                    ),
                onClick = { onTabPressed(navItem.name) },
            )
        }
    }
}
