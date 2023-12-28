package com.example.trainapp.data

import com.example.trainapp.network.TrainComponentApiService
import com.example.trainapp.network.asDomainObjects
import java.util.concurrent.CompletionService

interface TrainRepository {
    suspend fun getTrainComponents(): List<TrainComponent>
    suspend fun getTrainComponent(id: Int): TrainComponent
}

class ApiTrainRepository(
    private val trainApiService: TrainComponentApiService
) : TrainRepository {
    override suspend fun getTrainComponents() =
        trainApiService.getTrainComponents().asDomainObjects()

    override suspend fun getTrainComponent(id: Int): TrainComponent {
        trainApiService.getTrainComponentById(id).let {
            return it.let {
                TrainComponent(
                    id = it.id,
                    type = it.type,
                    subtype = it.subtype,
                    image = it.image,
                    descriptionImage = it.descriptionImage,
                    description = it.description
                )
            }
        }
    }
}