package com.example.trainapp.ui.trainOverview

import com.example.trainapp.data.TrainComponent

data class TrainUiState (
    val trains: List<TrainComponent> = listOf(),
)