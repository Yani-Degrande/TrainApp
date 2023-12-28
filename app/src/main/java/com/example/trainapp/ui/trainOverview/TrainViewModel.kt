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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.data.TrainRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class TrainViewModel(
    private val trainRepository: TrainRepository
) : ViewModel() {
    private val _trainUiState = MutableStateFlow(TrainUiState(trains = TrainComponent.getAll()))
    val trainUiState = _trainUiState.asStateFlow()

    var trainApiState: TrainApiState by mutableStateOf(TrainApiState.Loading)
        private set
    init {
        getApiTrainComponents()
    }

    private fun getApiTrainComponents() {
        viewModelScope.launch {
            try {
                val result = trainRepository.getTrainComponents()
                _trainUiState.update {
                    it.copy(trains = result)
                }
                trainApiState = TrainApiState.Success(result)
            } catch (e: SocketTimeoutException) {
                trainApiState = TrainApiState.Error
            } catch (e: IOException) {
                trainApiState = TrainApiState.Error
            }
        }
    }

    fun retry() {
        trainApiState = TrainApiState.Loading
        getApiTrainComponents()
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