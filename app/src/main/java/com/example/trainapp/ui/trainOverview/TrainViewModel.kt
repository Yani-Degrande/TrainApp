package com.example.trainapp.ui.trainOverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
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
 * ViewModel for managing the UI-related data and operations of the Train Application.
 * It interacts with the [TrainRepository] to fetch and update train component data.
 *
 * @property trainRepository The repository to interact with train component data.
 */
class TrainViewModel(
    private val trainRepository: TrainRepository,
) : ViewModel() {
    /**
     * Internal MutableStateFlow to hold the UI state of the train overview.
     */
    private val _uiState = MutableStateFlow(TrainOverviewState())

    /**
     * Exposed immutable StateFlow of the UI state for observing changes in the train overview.
     */
    val uiState: StateFlow<TrainOverviewState> = _uiState.asStateFlow()

    /**
     * StateFlow to hold the list state of train components.
     */
    lateinit var uiListState: StateFlow<TrainComponentListState>

    /**
     * State representing the API status, can be Loading, Success, or Error.
     * It's observed by the UI to show appropriate feedback to the user.
     */
    var trainApiState: TrainApiState by mutableStateOf(TrainApiState.Loading)
        private set

    /**
     * StateFlow to hold the state of the Wi-Fi worker.
     */
    lateinit var wifiWorkerState: StateFlow<WorkerState>

    init {
        getRepoTrainComponents()
    }

    /**
     * Fetches train components from the repository and updates the UI state accordingly.
     */
    private fun getRepoTrainComponents() {
        try {
            viewModelScope.launch { trainRepository.refreshTrainComponents() }
            uiListState =
                trainRepository.getAllItems().map { TrainComponentListState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = TrainComponentListState(),
                    )
            trainApiState = TrainApiState.Success
            wifiWorkerState =
                trainRepository.wifiWorkInfo.map { WorkerState(it) }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = WorkerState(),
                )
        } catch (e: SocketTimeoutException) {
            trainApiState = TrainApiState.Error
        } catch (e: IOException) {
            trainApiState = TrainApiState.Error
        }
    }

    /**
     * Function to retry fetching train components in case of a failure.
     */
    fun retry() {
        trainApiState = TrainApiState.Loading
        getRepoTrainComponents()
    }

    /**
     * Factory for creating instances of [TrainViewModel].
     */
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = this[APPLICATION_KEY] as TrainApplication
                    val trainRepository = application.container.trainRepository
                    TrainViewModel(trainRepository)
                }
            }
    }
}
