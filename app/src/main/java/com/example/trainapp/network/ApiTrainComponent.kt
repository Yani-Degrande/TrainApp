package com.example.trainapp.network

import com.example.trainapp.data.TrainComponentType
import kotlinx.serialization.Serializable

@Serializable
data class ApiTrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String
)
