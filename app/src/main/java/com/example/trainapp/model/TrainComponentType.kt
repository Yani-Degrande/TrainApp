package com.example.trainapp.model

/**
 * Enumeration of different types of train components.
 * This enum defines the main categories of components that can be part of a train.
 *
 * - LOCOMOTIVE: Represents the engine or locomotive part of the train, typically providing the motive power.
 * - CARRIAGE: Represents the carriages or coaches of the train, used for carrying passengers or goods.
 * - TRAINSET: Represents a complete train set, which may include locomotives, carriages, and other components as a single unit.
 */
enum class TrainComponentType {
    LOCOMOTIVE,
    CARRIAGE,
    TRAINSET,
}
