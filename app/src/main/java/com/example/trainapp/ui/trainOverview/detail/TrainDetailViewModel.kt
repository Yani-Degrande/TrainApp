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

class TrainDetailViewModel (
    private val trainRepository: TrainRepository,
    private val trainComponentId: Int
) : ViewModel() {
    private val _trainDetailUiState = MutableStateFlow(TrainDetailUiState())
    val trainDetailUiState: StateFlow<TrainDetailUiState> = _trainDetailUiState.asStateFlow()

    lateinit var uiListState: StateFlow<TrainComponentState>

    var trainDetailApiState: TrainDetailApiState by mutableStateOf(TrainDetailApiState.Loading)
        private set

    init {
        getRepoTrainComponent()
    }
    private fun getRepoTrainComponent() {
        try {
            viewModelScope.launch { trainRepository.refreshTrainComponents() }
            uiListState = trainRepository.getItem(trainComponentId).map { TrainComponentState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = TrainComponentState()
                )
            trainDetailApiState = TrainDetailApiState.Success
        } catch (e: SocketTimeoutException) {
            trainDetailApiState = TrainDetailApiState.Error
        } catch (e: IOException) {
            trainDetailApiState = TrainDetailApiState.Error
        }
    }

    fun retry() {
        trainDetailApiState = TrainDetailApiState.Loading
        getRepoTrainComponent()
    }

    companion object {
        fun Factory(trainId: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TrainApplication
                val trainRepository = application.container.trainRepository
                TrainDetailViewModel(trainRepository, trainId)
            }
        }
    }
}