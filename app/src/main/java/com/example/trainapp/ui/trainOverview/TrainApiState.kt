package com.example.trainapp.ui.trainOverview

import com.example.trainapp.data.TrainComponent


data class TrainOverviewState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class TrainComponentListState(val trainComponentList: List<TrainComponent> = listOf())
sealed interface TrainApiState {
    object Error : TrainApiState
    object Loading : TrainApiState
    object Success : TrainApiState
}