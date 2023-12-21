package com.example.trainapp.ui.trainOverview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.network.TrainComponentApi.trainComponentService
import com.example.trainapp.ui.trainOverview.TrainUiState
import kotlinx.coroutines.launch

class TrainViewModel : ViewModel() {
    private val _trainUiState = MutableStateFlow(TrainUiState(trains = TrainComponent.getAll()))
    val trainUiState = _trainUiState.asStateFlow()

    init {
        Log.i("TrainViewModel", "TrainViewModel created!")
        getApiTrainComponents()
    }

    private fun getApiTrainComponents() {
        viewModelScope.launch {
            val result = trainComponentService.getTrainComponents()
            Log.i("TrainViewModel", "The train components are: $result")
            println("The train components are: $result")
        }
    }
}