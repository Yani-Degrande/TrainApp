package com.example.trainapp.ui.trainOverview.detail

import com.example.trainapp.data.TrainComponent

interface TrainDetailApiState {
    object Error : TrainDetailApiState
    object Loading : TrainDetailApiState
    data class Success(val train: TrainComponent) : TrainDetailApiState
}