package com.example.trainapp.ui.trainOverview.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trainapp.TrainApplication
import com.example.trainapp.data.TrainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel for managing the UI-related data and operations for train component details in the Train Application.
 * It interacts with the [TrainRepository] to fetch and update specific train component data.
 *
 * @property trainRepository The repository to interact with train component data.
 * @property trainComponentId The unique identifier of the train component for which details are being fetched.
 */
class TrainDetailViewModel(
    private val trainRepository: TrainRepository,
    private val trainComponentId: Int,
) : ViewModel() {
    /**
     * Internal MutableStateFlow to hold the UI state of the train detail.
     */
    private val _trainDetailUiState = MutableStateFlow(TrainDetailUiState())

    /**
     * Exposed immutable StateFlow of the UI state for observing changes in the train detail.
     */
    val trainDetailUiState: StateFlow<TrainDetailUiState> = _trainDetailUiState.asStateFlow()

    /**
     * StateFlow to hold the state of a specific train component.
     */
    lateinit var uiListState: StateFlow<TrainComponentState>

    /**
     * State representing the API status for train detail, can be Loading, Success, or Error.
     * It's observed by the UI to show appropriate feedback to the user.
     */
    var trainDetailApiState: TrainDetailApiState by mutableStateOf(TrainDetailApiState.Loading)
        private set

    init {
        getRepoTrainComponent()
    }

    /**
     * Fetches details of a specific train component from the repository and updates the UI state accordingly.
     */
    private fun getRepoTrainComponent() {
        try {
            viewModelScope.launch { trainRepository.refreshTrainComponents() }
            uiListState =
                trainRepository.getItem(trainComponentId).map { TrainComponentState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = TrainComponentState(),
                    )
            trainDetailApiState = TrainDetailApiState.Success
        } catch (e: SocketTimeoutException) {
            trainDetailApiState = TrainDetailApiState.Error
        } catch (e: IOException) {
            trainDetailApiState = TrainDetailApiState.Error
        }
    }

    /**
     * Function to retry fetching train component details in case of a failure.
     */
    fun retry() {
        trainDetailApiState = TrainDetailApiState.Loading
        getRepoTrainComponent()
    }

    /**
     * Factory for creating instances of [TrainDetailViewModel].
     *
     * @param trainId The unique identifier of the train component for which the ViewModel is being created.
     * @return A ViewModelProvider.Factory for creating [TrainDetailViewModel] instances.
     */
    companion object {
        fun Factory(trainId: Int): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TrainApplication
                    val trainRepository = application.container.trainRepository
                    TrainDetailViewModel(trainRepository, trainId)
                }
            }
    }
}
