package com.example.trainapp.ui.trainOverview

import androidx.work.WorkInfo
import com.example.trainapp.model.TrainComponent

/**
 * Represents the state of the train overview UI.
 * It holds information about the scrolling actions and the current scroll position in the list.
 *
 * @property scrollActionIdx An index to represent a specific scroll action.
 * @property scrollToItemIndex The index of the item to scroll to in the list.
 */
data class TrainOverviewState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

/**
 * Represents the state of a list of train components.
 * This class is used to hold and manage the list of train components in the UI.
 *
 * @property trainComponentList The list of [TrainComponent] objects.
 */
data class TrainComponentListState(val trainComponentList: List<TrainComponent> = listOf())

/**
 * Represents the state of a background worker, specifically for Wi-Fi related tasks.
 * Contains information about the worker's operation and status.
 *
 * @property workerInfo The [WorkInfo] associated with the worker. It's null if there's no ongoing work.
 */
data class WorkerState(val workerInfo: WorkInfo? = null)

/**
 * Represents the different states of a train component API request.
 * This sealed interface defines the possible states for API interactions such as loading, success, or error.
 */
sealed interface TrainApiState {
    object Error : TrainApiState

    object Loading : TrainApiState

    object Success : TrainApiState
}
