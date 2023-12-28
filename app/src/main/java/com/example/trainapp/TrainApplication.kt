package com.example.trainapp

import android.app.Application
import com.example.trainapp.data.AppContainer
import com.example.trainapp.data.DefaultAppContainer

class TrainApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}