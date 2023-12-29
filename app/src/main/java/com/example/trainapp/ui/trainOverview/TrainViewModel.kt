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
import com.example.trainapp.data.TrainComponent
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

class TrainViewModel(
    private val trainRepository: TrainRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrainOverviewState())
    val uiState: StateFlow<TrainOverviewState> = _uiState.asStateFlow()

    lateinit var uiListState: StateFlow<TrainComponentListState>

    var trainApiState: TrainApiState by mutableStateOf(TrainApiState.Loading)
        private set
    init {
        getRepoTrainComponents()
    }

    private fun getRepoTrainComponents() {
        try {
            viewModelScope.launch { trainRepository.refreshTrainComponents() }
            uiListState = trainRepository.getAllItems().map { TrainComponentListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = TrainComponentListState()
                )
            trainApiState = TrainApiState.Success
        } catch (e: SocketTimeoutException) {
            trainApiState = TrainApiState.Error
        } catch (e: IOException) {
            trainApiState = TrainApiState.Error
        }
    }

    fun retry() {
        trainApiState = TrainApiState.Loading
        getRepoTrainComponents()
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as TrainApplication
                val trainRepository = application.container.trainRepository
                TrainViewModel(trainRepository)
            }
        }
    }
}