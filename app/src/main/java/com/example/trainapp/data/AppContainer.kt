package com.example.trainapp.data

import android.content.Context
import com.example.trainapp.data.database.TrainComponentDatabase
import com.example.trainapp.network.NetworkConnectionInterceptor
import com.example.trainapp.network.TrainComponentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Defines the container for application-wide dependencies.
 * Provides access to the repository layer of the application.
 */
interface AppContainer {
    /**
     * A repository instance for handling train-related data.
     */
    val trainRepository: TrainRepository
}

/**
 * The default implementation of [AppContainer] that sets up and provides dependencies.
 * It includes network handling, retrofit setup, and repository instantiation.
 *
 * @property context The context used for various operations within the container.
 */
class DefaultAppContainer(
    private val context: Context,
) : AppContainer {
    /**
     * Network connection interceptor to monitor network availability.
     */
    private val networkCheck = NetworkConnectionInterceptor(context)

    /**
     * HTTP client configured with network connection interceptor.
     */
    private val client =
        OkHttpClient.Builder()
            .addInterceptor(networkCheck)
            .build()

    /**
     * Base URL for the network requests.
     */
    private val baseUrl = "https://androidapi-enp7.onrender.com"

    /**
     * Retrofit instance configured for making network requests.
     */
    private var retrofit: Retrofit =
        Retrofit.Builder()
            .addConverterFactory(
                Json.asConverterFactory("application/json".toMediaType()),
            )
            .baseUrl(baseUrl)
            .client(client)
            .build()

    /**
     * Lazy-initialized service for train component API calls.
     */
    private val trainComponentService: TrainComponentApiService by lazy {
        retrofit.create(TrainComponentApiService::class.java)
    }

    /**
     * Provides an instance of [TrainRepository] for handling train component data.
     * This repository is a singleton and lazily initialized.
     */
    override val trainRepository: TrainRepository by lazy {
        CashingTrainRepository(
            TrainComponentDatabase.getDatabase(context = context).trainComponentDao(),
            trainComponentService,
            context,
        )
    }
}
