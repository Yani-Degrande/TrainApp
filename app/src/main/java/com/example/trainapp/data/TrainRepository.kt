package com.example.trainapp.data

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.trainapp.data.database.TrainComponentDao
import com.example.trainapp.data.database.asDbTrainComponent
import com.example.trainapp.data.database.asDomainTrainComponent
import com.example.trainapp.data.database.asDomainTrainComponents
import com.example.trainapp.network.TrainComponentApiService
import com.example.trainapp.network.asDomainObjects
import com.example.trainapp.network.getTrainComponentsAsFlow
import com.example.trainapp.workerUtils.WifiNotificationWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException
import java.util.UUID

interface TrainRepository {

    // Get all items from the datasource
    fun getAllItems(): Flow<List<TrainComponent>>

    //one specific item
    fun getItem(id: Int): Flow<TrainComponent>

    suspend fun insert(item: TrainComponent)

    suspend fun refreshTrainComponents()

    var wifiWorkInfo: Flow<WorkInfo>

}
class CashingTrainRepository (
    private val trainComponentDao: TrainComponentDao,
    private val trainApiService: TrainComponentApiService,
    context: Context
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
    private var workID = UUID(1,2)
    //the manager is private to the repository
    private val workManager = WorkManager.getInstance(context)
    //the info function is public
    override var wifiWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfoByIdFlow(workID)

    override suspend fun refreshTrainComponents() {

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val requestBuilder = OneTimeWorkRequestBuilder<WifiNotificationWorker>()
        val request = requestBuilder.setConstraints(constraints).build()
        workManager.enqueue(request)
        workID = request.id
        wifiWorkInfo = workManager.getWorkInfoByIdFlow(request.id)


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