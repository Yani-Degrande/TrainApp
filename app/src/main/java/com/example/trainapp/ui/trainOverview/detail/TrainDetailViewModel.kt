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
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.data.TrainRepository
import com.example.trainapp.ui.trainOverview.TrainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class TrainDetailViewModel (
    private val trainRepository: TrainRepository,
    private val trainComponentId: Int
) : ViewModel() {
    private val _trainDetailUiState = MutableStateFlow(TrainDetailUiState(train = TrainComponent.getById(trainComponentId)))
    val trainDetailUiState = _trainDetailUiState.asStateFlow()

    var trainDetailApiState: TrainDetailApiState by mutableStateOf(TrainDetailApiState.Loading)
        private set

    init {
        getApiTrainDetailComponents()
    }

    private fun getApiTrainDetailComponents() {
        viewModelScope.launch {
            try {
                val result = trainRepository.getTrainComponent(trainComponentId)
                _trainDetailUiState.update {
                    it.copy(train = result)
                }
                trainDetailApiState = TrainDetailApiState.Success(result)
            } catch (e: SocketTimeoutException) {
                trainDetailApiState = TrainDetailApiState.Error
            } catch (e: IOException) {
                trainDetailApiState = TrainDetailApiState.Error
            }
        }
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