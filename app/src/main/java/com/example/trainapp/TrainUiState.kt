package com.example.trainapp

import com.example.trainapp.data.TrainComponent

data class TrainUiState (
    val trains: List<TrainComponent> = listOf(),
)