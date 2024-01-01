package com.example.trainapp.ui.trainOverview.detail

import com.example.trainapp.model.TrainComponent

/**
 * Represents the UI state for the train detail view.
 * It holds the details of a specific train component to be displayed.
 *
 * @property train The [TrainComponent] whose details are being displayed. It is null when no train component is selected or available.
 */
data class TrainDetailUiState(
    val train: TrainComponent? = null,
)

/**
 * Represents the state of a specific train component.
 * This class is used to hold and manage the details of a train component in the UI.
 *
 * @property trainComponent The [TrainComponent] object containing the details of a train component. It is null if the component is not loaded or unavailable.
 */
data class TrainComponentState(
    val trainComponent: TrainComponent? = null,
)

/**
 * Represents the different states of a train component detail API request.
 * This sealed interface defines the possible states for API interactions when fetching train component details, such as loading, success, or error.
 *
 * - [Error]: Indicates that there was an error in fetching train component details.
 * - [Loading]: Represents the state where the train component details are being loaded.
 * - [Success]: Indicates that the train component details were successfully loaded.
 */
sealed interface TrainDetailApiState {
    object Error : TrainDetailApiState

    object Loading : TrainDetailApiState

    object Success : TrainDetailApiState
}
