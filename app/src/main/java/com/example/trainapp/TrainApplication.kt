package com.example.trainapp

import android.app.Application
import com.example.trainapp.data.AppContainer
import com.example.trainapp.data.DefaultAppContainer

/**
 * Custom application class for the Train Application.
 * Initializes application-level dependencies and configurations.
 *
 * This class is responsible for setting up the dependency container used throughout the application.
 */
class TrainApplication : Application() {
    /**
     * The dependency container that holds application-wide instances like repositories, database access objects, etc.
     * It is initialized in the onCreate() method.
     */

    lateinit var container: AppContainer

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Initializes the [AppContainer] with the application context.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
