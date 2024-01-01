package com.example.trainapp.network

import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for making API calls related to train components.
 * Methods in this interface are used to fetch train component data from a remote server.
 */
interface TrainComponentApiService {
    /**
     * Fetches a list of train components from the server.
     * This function should be called within a coroutine scope as it is a suspending function.
     *
     * @return A list of [ApiTrainComponent]s representing train components fetched from the API.
     */
    @GET("/api/trainComponents")
    suspend fun getTrainComponents(): List<ApiTrainComponent>

    /**
     * Fetches a specific train component by its ID from the server.
     * This function should be called within a coroutine scope as it is a suspending function.
     *
     * @param id The unique identifier of the train component to fetch.
     * @return An [ApiTrainComponent] instance representing the train component with the specified ID.
     */
    @GET("/api/trainComponents/{id}")
    suspend fun getTrainComponentById(
        @Path("id") id: Int,
    ): ApiTrainComponent
}

/**
 * Extension function to wrap [getTrainComponents] API call in a Kotlin Flow.
 * This enables the API call to be reactive and provides a way to handle exceptions gracefully.
 *
 * @receiver The [TrainComponentApiService] on which this function is an extension.
 * @return A [Flow] emitting a list of [ApiTrainComponent]s.
 */
fun TrainComponentApiService.getTrainComponentsAsFlow() =
    flow {
        try {
            emit(getTrainComponents())
        } catch (e: Exception) {
            Log.e("API", "getTrainComponentsAsFlow: ${e.stackTraceToString()}", e)
        }
    }
