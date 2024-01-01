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
import com.example.trainapp.model.TrainComponent
import com.example.trainapp.network.TrainComponentApiService
import com.example.trainapp.network.asDomainObjects
import com.example.trainapp.network.getTrainComponentsAsFlow
import com.example.trainapp.workerUtils.WifiNotificationWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException
import java.util.UUID

/**
 * Interface defining the operations for managing train components.
 * Provides methods for retrieving and inserting train component data.
 */
interface TrainRepository {
    /**
     * Retrieves all train components.
     *
     * @return A [Flow] of a list of [TrainComponent] instances.
     */
    fun getAllItems(): Flow<List<TrainComponent>>

    /**
     * Retrieves a specific train component by its ID.
     *
     * @param id The unique identifier of the train component.
     * @return A [Flow] emitting the specified [TrainComponent].
     */
    fun getItem(id: Int): Flow<TrainComponent>

    /**
     * Inserts a train component into the repository.
     *
     * @param item The [TrainComponent] to be inserted.
     */
    suspend fun insert(item: TrainComponent)

    /**
     * Refreshes the train components by fetching them from a remote source.
     */
    suspend fun refreshTrainComponents()

    /**
     * Provides information about the Wi-Fi work status.
     *
     * @return A [Flow] of [WorkInfo] providing updates on the Wi-Fi work request.
     */
    var wifiWorkInfo: Flow<WorkInfo>
}

/**
 * A repository implementation that caches train components.
 * Handles data operations and provides train components from a local database or a remote API.
 *
 * @property trainComponentDao The DAO for accessing train components in the local database.
 * @property trainApiService The API service for fetching train components from a remote source.
 * @property context The context for creating work requests.
 */
class CashingTrainRepository(
    private val trainComponentDao: TrainComponentDao,
    private val trainApiService: TrainComponentApiService,
    context: Context,
) : TrainRepository {
    override suspend fun insert(item: TrainComponent) {
        trainComponentDao.insert(item.asDbTrainComponent())
    }

    // this repo contains logic to refresh the trainComponents (remote)
    // sometimes that logic is written in a 'usecase'
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

    /**
     * Unique ID for the work request.
     */
    private var workID = UUID(1, 2)

    /**
     * Manager to handle background work requests.
     */
    private val workManager = WorkManager.getInstance(context)

    // the info function is public
    override var wifiWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfoByIdFlow(workID)

    override suspend fun refreshTrainComponents() {
        // refresh is used to schedule the workrequest
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val requestBuilder = OneTimeWorkRequestBuilder<WifiNotificationWorker>()
        val request = requestBuilder.setConstraints(constraints).build()
        workManager.enqueue(request)
        workID = request.id
        wifiWorkInfo = workManager.getWorkInfoByIdFlow(request.id)

        // note the actual api request still uses coroutines
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
