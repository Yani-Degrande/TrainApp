package com.example.trainapp.network

import com.example.trainapp.data.TrainComponent
import com.example.trainapp.data.TrainComponentType
import kotlinx.serialization.Serializable

@Serializable
data class ApiTrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String
)

fun List<ApiTrainComponent>.asDomainObjects()= map {
    TrainComponent(
        id = it.id,
        type = it.type,
        subtype = it.subtype,
        image = it.image
    )
}
