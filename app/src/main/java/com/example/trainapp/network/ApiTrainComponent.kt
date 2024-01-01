package com.example.trainapp.network

import com.example.trainapp.model.TrainComponent
import com.example.trainapp.model.TrainComponentType
import kotlinx.serialization.Serializable

/**
 * Data class representing a train component from an API response.
 * This class is used to parse the JSON response from an API into a Kotlin object.
 * It includes all the necessary information about a train component.
 *
 * @property id The unique identifier for the train component.
 * @property type The type of the train component (e.g., LOCOMOTIVE, CARRIAGE, TRAINSET).
 * @property subtype A more specific categorization or model of the train component.
 * @property image URL or reference to an image representing the train component.
 * @property descriptionImage URL or reference to an image that provides additional description.
 * @property description Textual description of the train component, detailing its features and other relevant information.
 */
@Serializable
data class ApiTrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String,
    val descriptionImage: String,
    val description: String,
)

/**
 * Extension function for converting a list of [ApiTrainComponent]s to a list of domain model [TrainComponent]s.
 * Useful for mapping data models from API responses to the domain layer models.
 *
 * @receiver List of [ApiTrainComponent]s to be converted.
 * @return A list of [TrainComponent]s corresponding to the receiver.
 */
fun List<ApiTrainComponent>.asDomainObjects() =
    map {
        TrainComponent(
            id = it.id,
            type = it.type,
            subtype = it.subtype,
            image = it.image,
            descriptionImage = it.descriptionImage,
            description = it.description,
        )
    }
