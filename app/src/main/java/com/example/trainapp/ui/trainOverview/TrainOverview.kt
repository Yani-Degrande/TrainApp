package com.example.trainapp.ui.trainOverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainapp.TrainViewModel
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.data.TrainComponentType
import com.example.trainapp.ui.trainOverview.components.TrainComponentList

@Composable
fun TrainOverview(innerPadding: PaddingValues) {
    val viewModel : TrainViewModel = viewModel()
    val trainUiState by viewModel.trainUiState.collectAsState()

    val locomotives = trainUiState.trains.filter { it.type == TrainComponentType.LOCOMOTIVE }
    val carriages = trainUiState.trains.filter { it.type == TrainComponentType.CARRIAGE }
    val trainsets = trainUiState.trains.filter { it.type == TrainComponentType.TRAINSET }

    Box(modifier = Modifier.padding(innerPadding)) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
        ) {
            item {
                TrainComponentList(locomotives, "Locomotieven")
                Spacer(modifier = Modifier.height(16.dp))
                TrainComponentList(carriages, "Rijtuigen")
                Spacer(modifier = Modifier.height(16.dp))
                TrainComponentList(trainsets, "Treinstellen")
            }
        }
    }
}