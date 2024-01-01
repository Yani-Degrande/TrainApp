package com.example.trainapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for accessing train component data.
 * Provides methods for interacting with the trainComponents table in the database.
 */
@Dao
interface TrainComponentDao {
    /**
     * Inserts a train component into the database.
     * If the component already exists, it replaces the existing one.
     *
     * @param item The [DbTrainComponent] to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbTrainComponent)

    /**
     * Retrieves all train components from the database.
     *
     * @return A [Flow] emitting a list of [DbTrainComponent]s, reflecting any changes in the database.
     */
    @Query("SELECT * FROM trainComponents")
    fun getAllItems(): Flow<List<DbTrainComponent>>

    /**
     * Retrieves a specific train component by its ID.
     *
     * @param id The unique identifier of the train component to be retrieved.
     * @return A [Flow] emitting the [DbTrainComponent] with the specified ID.
     */
    @Query("SELECT * FROM trainComponents WHERE id = :id")
    fun getItem(id: Int): Flow<DbTrainComponent>
}
