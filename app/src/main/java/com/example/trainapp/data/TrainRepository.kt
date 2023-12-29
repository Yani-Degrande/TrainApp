package com.example.trainapp.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainapp.data.database.DbTrainComponent
import com.example.trainapp.data.database.TrainComponentDao
import com.example.trainapp.data.database.asDbTrainComponent
import com.example.trainapp.data.database.asDomainTrainComponent
import com.example.trainapp.data.database.asDomainTrainComponents
import com.example.trainapp.network.TrainComponentApiService
import com.example.trainapp.network.asDomainObjects
import com.example.trainapp.network.getTrainComponentsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException
import java.util.concurrent.CompletionService

interface TrainRepository {

    // Get all items from the datasource
    fun getAllItems(): Flow<List<TrainComponent>>

    //one specific item
    fun getItem(id: Int): Flow<TrainComponent>

    suspend fun insert(item: TrainComponent)

    suspend fun refreshTrainComponents()
}
class CashingTrainRepository (
    private val trainComponentDao: TrainComponentDao,
    private val trainApiService: TrainComponentApiService
): TrainRepository {
    override suspend fun insert(item: TrainComponent) {
        trainComponentDao.insert(item.asDbTrainComponent())
    }

    override fun getAllItems(): Flow<List<TrainComponent>> {
        return trainComponentDao.getAllItems().map {
            it.asDomainTrainComponents()
        }.onEach {
            if (it.isEmpty()) {
                refreshTrainComponents()
            }
        }
    }
    override fun getItem(id: Int): Flow<TrainComponent> {
        return trainComponentDao.getItem(id).map {
            it.asDomainTrainComponent()
        }
    }
    override suspend fun refreshTrainComponents() {
        try {
            trainApiService.getTrainComponentsAsFlow().collect {
                for (trainComponent in it.asDomainObjects()) {
                    insert(trainComponent)
                }
            }
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
        }
    }
}