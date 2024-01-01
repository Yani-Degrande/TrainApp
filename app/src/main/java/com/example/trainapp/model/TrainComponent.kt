package com.example.trainapp.model

/**
 * Represents a train component with detailed information.
 * This data class encapsulates the properties of a train component used in the application.
 *
 * @property id The unique identifier for the train component.
 * @property type The type of the train component (e.g., locomotive, carriage).
 * @property subtype A more specific categorization or model of the train component.
 * @property image URL or reference to an image representing the train component.
 * @property descriptionImage URL or reference to an image that provides additional description.
 * @property description Textual description of the train component, detailing its features and other relevant information.
 */
data class TrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String,
    val descriptionImage: String,
    val description: String,
)
