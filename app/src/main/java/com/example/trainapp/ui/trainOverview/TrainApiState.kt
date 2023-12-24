package com.example.trainapp.ui.trainOverview

import com.example.trainapp.data.TrainComponent

sealed interface TrainApiState {
    object Error : TrainApiState
    object Loading : TrainApiState
    data class Success(val trains: List<TrainComponent>) : TrainApiState
}