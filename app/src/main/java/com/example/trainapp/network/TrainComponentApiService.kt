package com.example.trainapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface TrainComponentApiService {
    @GET("/api/trainComponents")
    suspend fun getTrainComponents(): List<ApiTrainComponent>

    @GET("/api/trainComponents/{id}")
    suspend fun getTrainComponentById(id: Int): ApiTrainComponent
}

