package com.example.trainapp.fake

import androidx.work.Data
import androidx.work.WorkInfo
import com.example.trainapp.data.TrainRepository
import com.example.trainapp.model.TrainComponent
import com.example.trainapp.model.TrainComponentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class FakeTrainRepository : TrainRepository {
    // Assume we have a mutable list to represent the internal state of train components
    private val _trainComponents = mutableListOf<TrainComponent>()
    private val _trainComponentsFlow = MutableStateFlow<List<TrainComponent>>(emptyList())

    // Initialize with a default WorkInfo object with appropriate initial state
    private var _wifiWorkInfo =
        MutableStateFlow<WorkInfo>(
            WorkInfo(
                id = UUID.randomUUID(),
                state = WorkInfo.State.ENQUEUED,
                outputData = Data.EMPTY,
                tags = setOf(),
                progress = Data.EMPTY,
                runAttemptCount = 0,
            ),
        )

    override fun getAllItems(): Flow<List<TrainComponent>> {
        return flowOf(emptyList<TrainComponent>())
    }

    override fun getItem(id: Int): Flow<TrainComponent> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(item: TrainComponent) {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTrainComponents() {
        // Simulate refreshing the train components by updating the list and emitting a new value
        val refreshedComponents =
            listOf(
                TrainComponent(
                    id = 1,
                    type = TrainComponentType.LOCOMOTIVE,
                    subtype = "Electric",
                    image = "url_to_image",
                    descriptionImage = "url_to_description_image",
                    description = "A newly refreshed train component",
                ),
            )
        _trainComponents.clear()
        _trainComponents.addAll(refreshedComponents)
        _trainComponentsFlow.emit(refreshedComponents)
    }

    // Override the wifiWorkInfo property to use the backing field
    override var wifiWorkInfo: Flow<WorkInfo>
        get() = _wifiWorkInfo.asStateFlow() // Return as a read-only StateFlow
        set(value) {
            // This assumes that you want to replace the existing flow with a new one.
            // If you need to update the value inside the flow, you will need additional logic.
            _wifiWorkInfo = value as MutableStateFlow<WorkInfo>
        }
}
