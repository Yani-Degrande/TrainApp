package com.example.trainapp.ui.trainOverview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainapp.data.TrainComponentType
import com.example.trainapp.ui.components.ErrorMessage
import com.example.trainapp.ui.trainOverview.components.TrainComponentList

@Composable
fun TrainOverview(innerPadding: PaddingValues, viewModel : TrainViewModel = viewModel(factory = TrainViewModel.Factory), onTrainComponentClick: (Int) -> Unit) {
    val trainUiState by viewModel.trainUiState.collectAsState()

    Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
        ) {
            val trainApiState = viewModel.trainApiState
            when (trainApiState) {
                is TrainApiState.Error -> {
                    item {
                        ErrorMessage(errorMessage = "Oops, er is iets misgegaan! Probeer het opnieuw."
                            , onRetry = { viewModel.retry() })
                    }
                }
                is TrainApiState.Loading -> {
                    item {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is TrainApiState.Success -> {

                    val locomotives = trainApiState.trains.filter { it.type == TrainComponentType.LOCOMOTIVE }
                    val carriages = trainApiState.trains.filter { it.type == TrainComponentType.CARRIAGE }
                    val trainsets = trainApiState.trains.filter { it.type == TrainComponentType.TRAINSET }

                    item {
                        TrainComponentList(locomotives, "Locomotieven", onTrainComponentClick)
                        Spacer(modifier = Modifier.height(16.dp))
                        TrainComponentList(carriages, "Rijtuigen", onTrainComponentClick)
                        Spacer(modifier = Modifier.height(16.dp))
                        TrainComponentList(trainsets, "Treinstellen", onTrainComponentClick)
                    }
                }
            }

        }
    }
}