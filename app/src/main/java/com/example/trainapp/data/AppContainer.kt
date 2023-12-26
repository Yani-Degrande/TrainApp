package com.example.trainapp.data

import com.example.trainapp.network.TrainComponentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val trainRepository: TrainRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:3000"

    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val trainComponentService: TrainComponentApiService by lazy {
        retrofit.create(TrainComponentApiService::class.java)
    }
    override val trainRepository: TrainRepository by lazy {
        ApiTrainRepository(trainComponentService)
    }
}