package com.example.trainapp.ui.trainOverview

import androidx.work.WorkInfo
import com.example.trainapp.data.TrainComponent


data class TrainOverviewState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class TrainComponentListState(val trainComponentList: List<TrainComponent> = listOf())
data class WorkerState(val workerInfo: WorkInfo? = null)

sealed interface TrainApiState {
    object Error : TrainApiState
    object Loading : TrainApiState
    object Success : TrainApiState
}