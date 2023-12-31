package com.example.trainapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainapp.data.TrainComponentSampler
import com.example.trainapp.model.TrainComponent
import com.example.trainapp.model.TrainComponentType

@Entity(tableName = "trainComponents")
data class DbTrainComponent (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: TrainComponentType,
    val subtype: String,
    val image: String,
    val descriptionImage: String,
    val description: String
)

fun TrainComponent.asDbTrainComponent(): DbTrainComponent {
    return DbTrainComponent(
        id = id,
        type = type,
        subtype = subtype,
        image = image,
        descriptionImage = descriptionImage,
        description = description
    )
}

fun DbTrainComponent.asDomainTrainComponent(): TrainComponent {
    return TrainComponent(
        id = id,
        type = type,
        subtype = subtype,
        image = image,
        descriptionImage = descriptionImage,
        description = description
    )
}
fun List<DbTrainComponent>.asDomainTrainComponents() = map {it.asDomainTrainComponent()}