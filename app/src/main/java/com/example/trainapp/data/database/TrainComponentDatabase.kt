package com.example.trainapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbTrainComponent::class], version = 2, exportSchema = false)
abstract class TrainComponentDatabase : RoomDatabase() {
    abstract fun trainComponentDao(): TrainComponentDao

    companion object {
        @Volatile
        private var Instance: TrainComponentDatabase? = null

        fun getDatabase(context: Context): TrainComponentDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TrainComponentDatabase::class.java, "traincomponent_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}