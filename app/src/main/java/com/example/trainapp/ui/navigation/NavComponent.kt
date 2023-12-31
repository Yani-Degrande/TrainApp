package com.example.trainapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trainapp.ui.startOverview.StartOverview
import com.example.trainapp.ui.teamOverview.TeamOverview
import com.example.trainapp.ui.trainOverview.TrainOverview
import com.example.trainapp.ui.trainOverview.detail.TrainDetailsOverview

@Composable
fun NavComponent(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController, startDestination = Destinations.Start.name) {
        composable(route = Destinations.Start.name) {
            StartOverview(innerPadding)

        }
        composable(route = Destinations.Train.name) {
            TrainOverview(innerPadding = innerPadding, onTrainComponentClick = { trainId ->
                navController.navigate("${Destinations.Train.name}/$trainId")
            })
        }
        composable(route = "${Destinations.Train.name}/{trainId}") { backStackEntry ->
            val trainId = backStackEntry.arguments?.getString("trainId")?.toInt() ?: 0
            TrainDetailsOverview(innerPadding, trainId)
        }

        composable(route = Destinations.Teams.name) {
            TeamOverview(innerPadding)
        }
    }
}