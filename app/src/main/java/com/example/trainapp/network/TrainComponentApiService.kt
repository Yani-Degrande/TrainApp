package com.example.trainapp.network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainComponentApiService {
    @GET("/api/trainComponents")
    suspend fun getTrainComponents(): List<ApiTrainComponent>

    @GET("/api/trainComponents/{id}")
    suspend fun getTrainComponentById(@Path("id") id: Int): ApiTrainComponent
}


fun TrainComponentApiService.getTrainComponentsAsFlow() = flow {
    try {
        emit(getTrainComponents())
    } catch (e: Exception) {
        Log.e("API", "getTrainComponentsAsFlow: ${e.stackTraceToString()}", e)
    }
}

