package com.example.trainapp.ui.trainOverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.network.TrainComponentApi.trainComponentService
import com.example.trainapp.network.asDomainObjects
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class TrainViewModel : ViewModel() {
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
                val result = trainComponentService.getTrainComponents()
                trainApiState = TrainApiState.Success(result.asDomainObjects())
            } catch (e: SocketTimeoutException) {
                trainApiState = TrainApiState.Error
            } catch (e: IOException) {
                trainApiState = TrainApiState.Error
            }
        }
    }
}