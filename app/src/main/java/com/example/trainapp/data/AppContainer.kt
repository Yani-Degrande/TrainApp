package com.example.trainapp.data

import android.content.Context
import androidx.room.Room
import com.example.trainapp.data.database.TrainComponentDao
import com.example.trainapp.data.database.TrainComponentDatabase
import com.example.trainapp.network.TrainComponentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val trainRepository: TrainRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    private val baseUrl = "https://androidapi-enp7.onrender.com"

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
        CashingTrainRepository(
            TrainComponentDatabase.getDatabase(context = context).trainComponentDao(), trainComponentService)
    }
}