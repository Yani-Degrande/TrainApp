package com.example.trainapp.model

data class TrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String,
    val descriptionImage: String,
    val description: String
)
