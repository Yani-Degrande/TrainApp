package com.example.trainapp.network

import com.example.trainapp.model.TrainComponent
import com.example.trainapp.model.TrainComponentType
import kotlinx.serialization.Serializable
import java.util.concurrent.Flow

@Serializable
data class ApiTrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String,
    val descriptionImage: String,
    val description: String
)

fun List<ApiTrainComponent>.asDomainObjects()= map {
    TrainComponent(
        id = it.id,
        type = it.type,
        subtype = it.subtype,
        image = it.image,
        descriptionImage = it.descriptionImage,
        description = it.description
    )
}