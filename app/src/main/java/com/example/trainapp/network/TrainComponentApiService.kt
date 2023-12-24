package com.example.trainapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface TrainComponentApiService {
    @GET("/api/trainComponents")
    suspend fun getTrainComponents(): List<ApiTrainComponent>
}

private var retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl("http://10.0.2.2:3000")
    .build()

object TrainComponentApi {
    val trainComponentService: TrainComponentApiService by lazy {
        retrofit.create(TrainComponentApiService::class.java)
    }
}