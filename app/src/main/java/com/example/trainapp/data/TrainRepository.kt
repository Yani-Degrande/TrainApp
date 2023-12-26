package com.example.trainapp.data

import com.example.trainapp.network.TrainComponentApiService
import com.example.trainapp.network.asDomainObjects
import java.util.concurrent.CompletionService

interface TrainRepository {
    suspend fun getTrainComponents(): List<TrainComponent>
}

class ApiTrainRepository(
    private val trainApiService: TrainComponentApiService
) : TrainRepository {
    override suspend fun getTrainComponents() =
        trainApiService.getTrainComponents().asDomainObjects()
}