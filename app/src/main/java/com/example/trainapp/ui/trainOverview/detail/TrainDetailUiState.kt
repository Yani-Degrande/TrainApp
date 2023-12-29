package com.example.trainapp.ui.trainOverview.detail

import com.example.trainapp.data.TrainComponent

data class TrainDetailUiState (
    val train: TrainComponent? = null
)

data class TrainComponentState(
    val trainComponent: TrainComponent? = null
)

sealed interface TrainDetailApiState {
    object Error : TrainDetailApiState
    object Loading : TrainDetailApiState
    object Success: TrainDetailApiState
}