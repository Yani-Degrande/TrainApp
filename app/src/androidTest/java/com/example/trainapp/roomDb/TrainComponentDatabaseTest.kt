package com.example.trainapp.roomDb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.trainapp.data.database.TrainComponentDatabase
import com.example.trainapp.data.database.asDbTrainComponent
import com.example.trainapp.model.TrainComponent
import com.example.trainapp.model.TrainComponentType
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrainComponentDatabaseTest {
    private lateinit var db: TrainComponentDatabase

    @Before
    fun createDb() {
        // Context of the app under test.
        val context = ApplicationProvider.getApplicationContext<Context>()
        db =
            Room.inMemoryDatabaseBuilder(context, TrainComponentDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeEventAndReadInList() =
        runBlocking {
            val dao = db.trainComponentDao()
            val trainComponent =
                TrainComponent(
                    id = 1,
                    type = TrainComponentType.LOCOMOTIVE,
                    subtype = "Test",
                    description = "Test",
                    descriptionImage = "Test",
                    image = "Test",
                )

            dao.insert(trainComponent.asDbTrainComponent())
            val byId = dao.getItem(1).first()
            assertThat(byId.id, equalTo(trainComponent.id))
        }

    @Test
    @Throws(Exception::class)
    fun testGetAllItems() =
        runBlocking {
            val dao = db.trainComponentDao()
            // Insert multiple train components
            val trainComponents =
                listOf(
                    TrainComponent(1, TrainComponentType.LOCOMOTIVE, "Subtype1", "Description1", "ImageDesc1", "Image1"),
                    TrainComponent(2, TrainComponentType.CARRIAGE, "Subtype2", "Description2", "ImageDesc2", "Image2"),
                )
            trainComponents.forEach { dao.insert(it.asDbTrainComponent()) }

            // Retrieve all items
            val allItems = dao.getAllItems().first()
            assertThat(allItems.size, equalTo(trainComponents.size))
        }

    @Test
    @Throws(Exception::class)
    fun testInsertConflictResolution() =
        runBlocking {
            val dao = db.trainComponentDao()
            val initialComponent = TrainComponent(1, TrainComponentType.LOCOMOTIVE, "Subtype", "Description", "ImageDesc", "Image")
            dao.insert(initialComponent.asDbTrainComponent())

            // Inserting a new component with the same ID but different details
            val newComponent = TrainComponent(1, TrainComponentType.CARRIAGE, "NewSubtype", "NewDescription", "NewImageDesc", "NewImage")
            dao.insert(newComponent.asDbTrainComponent())

            val retrievedComponent = dao.getItem(1).first()
            assertThat(retrievedComponent.subtype, equalTo(newComponent.subtype))
        }

    @Test
    @Throws(Exception::class)
    fun testEmptyDatabase() =
        runBlocking {
            val dao = db.trainComponentDao()
            val allItems = dao.getAllItems().first()
            assertTrue(allItems.isEmpty())
        }

    @Test
    @Throws(Exception::class)
    fun testInsertMultipleItemsAndCount() =
        runBlocking {
            val dao = db.trainComponentDao()
            val trainComponents =
                listOf(
                    TrainComponent(1, TrainComponentType.LOCOMOTIVE, "Subtype1", "Description1", "ImageDesc1", "Image1"),
                    TrainComponent(2, TrainComponentType.CARRIAGE, "Subtype2", "Description2", "ImageDesc2", "Image2"),
                    TrainComponent(3, TrainComponentType.LOCOMOTIVE, "Subtype3", "Description3", "ImageDesc3", "Image3"),
                )
            trainComponents.forEach { dao.insert(it.asDbTrainComponent()) }

            val allItems = dao.getAllItems().first()
            assertThat(allItems.size, equalTo(3))
        }

    @Test
    @Throws(Exception::class)
    fun testRetrieveNonExistentItem() =
        runBlocking {
            val dao = db.trainComponentDao()
            val retrievedComponent = dao.getItem(99).firstOrNull()
            assertNull(retrievedComponent)
        }
}
