package com.example.trainapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainComponentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbTrainComponent)

    @Query("SELECT * FROM trainComponents")
    fun getAllItems(): Flow<List<DbTrainComponent>>

    @Query("SELECT * FROM trainComponents WHERE id = :id")
    fun getItem(id: Int): Flow<DbTrainComponent>
}