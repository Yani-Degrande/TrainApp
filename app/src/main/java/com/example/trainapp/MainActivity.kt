package com.example.trainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainapp.ui.navigation.BottomAppBar
import com.example.trainapp.ui.navigation.Destinations
import com.example.trainapp.ui.navigation.TopAppBar
import com.example.trainapp.ui.theme.TrainAppTheme
import com.example.trainapp.ui.trainOverview.TrainOverview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TrainAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrainApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainApp(){
    var selectedItem by remember { mutableStateOf(0) }
    val trainIcon = painterResource(R.drawable.train_fill0_wght400_grad0_opsz24)
    val groupIcon = painterResource(R.drawable.group_fill0_wght400_grad0_opsz24)

    val icons = listOf(trainIcon, groupIcon)
    val items = listOf("Treinen", "Ploegen")

    val navController = rememberNavController()

    var topAppBarTitle by remember { mutableStateOf("Train App") }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                topAppBarTitle = topAppBarTitle,
                modifier = Modifier.padding(8.dp)
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
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "Trains") {
            composable(route= Destinations.Trains.name) {
                topAppBarTitle = "Treinen"
                TrainOverview(innerPadding = innerPadding)
            }

            composable(route = Destinations.Teams.name) {
                Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Selected item: ${items[selectedItem]}",
                        modifier = Modifier.padding(innerPadding)
                    )
                    topAppBarTitle = "Ploegen"
                }

            }
        }
    }
}




