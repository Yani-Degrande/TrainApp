package com.example.trainapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trainapp.R
import com.example.trainapp.ui.components.NavigationDrawerContent
import com.example.trainapp.ui.components.TrainNavigationRail
import com.example.trainapp.ui.navigation.BottomAppBar
import com.example.trainapp.ui.navigation.Destinations
import com.example.trainapp.ui.navigation.NavComponent
import com.example.trainapp.ui.navigation.TopAppBar
import com.example.trainapp.ui.navigation.pair
import com.example.trainapp.ui.util.TrainNavigationType

/**
 * The main composable function for the Train Application.
 * It sets up the UI structure based on the specified navigation type (e.g., bottom navigation, navigation rail, permanent drawer).
 * This function dynamically changes the navigation and layout according to the provided [navigationType].
 *
 * This function uses the ExperimentalMaterial3Api class, indicating that it relies on an API
 * that is experimental and may change in the future.
 *
 * @param navigationType The type of navigation to be used in the app (e.g., bottom navigation, navigation rail).
 * @param navController The navigation controller for navigating between screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainApp(
    navigationType: TrainNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    var selectedItem by remember { mutableStateOf(0) }
    val (icons, items) = pair()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isDetailPage = currentRoute?.startsWith("${Destinations.Train.name}/") == true

    if (navigationType == TrainNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                NavigationDrawerContent(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                    modifier =
                        Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(dimensionResource(R.dimen.drawer_padding_content)),
                )
            }
        }) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(8.dp),
                        when (selectedItem) {
                            0 -> R.string.train_app_title
                            1 -> R.string.trains
                            2 -> R.string.groups
                            else -> R.string.train_app_title
                        },
                        showBackButton = isDetailPage,
                        onBackButtonPressed = { navController.popBackStack() },
                    )
                },
            ) { innerPadding ->
                NavComponent(navController, innerPadding)
            }
        }
    } else if (navigationType == TrainNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(8.dp),
                    when (selectedItem) {
                        0 -> R.string.train_app_title
                        1 -> R.string.trains
                        2 -> R.string.groups
                        else -> R.string.train_app_title
                    },
                    showBackButton = isDetailPage,
                    onBackButtonPressed = { navController.popBackStack() },
                )
            },
            bottomBar = {
                BottomAppBar(
                    items = items,
                    icons = icons,
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        navController.navigate(Destinations.values()[index].name)
                    },
                )
            },
        ) { innerPadding ->
            NavComponent(navController, innerPadding)
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == TrainNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                TrainNavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(8.dp),
                        when (selectedItem) {
                            0 -> R.string.train_app_title
                            1 -> R.string.trains
                            2 -> R.string.groups
                            else -> R.string.train_app_title
                        },
                        showBackButton = isDetailPage,
                        onBackButtonPressed = { navController.popBackStack() },
                    )
                },
            ) { innerPadding ->
                NavComponent(navController, innerPadding)
            }
        }
    }
}
