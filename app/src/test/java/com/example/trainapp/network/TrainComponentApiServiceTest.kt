package com.example.trainapp.network

import com.example.trainapp.model.TrainComponentType
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrainComponentApiServiceTest {
    private lateinit var service: TrainComponentApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrainComponentApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getTrainComponents returns list successfully`() =
        runBlocking {
            val sampleApiResponse = "[{\"id\": 1, \"type\": \"LOCOMOTIVE\", \"description\": \"Sample Description\"}]"
            mockWebServer.enqueue(MockResponse().setBody(sampleApiResponse))

            val response = service.getTrainComponents()

            assertNotNull(response)
            assertEquals(1, response.size)
            assertEquals(TrainComponentType.LOCOMOTIVE, response[0].type)
        }

    @Test
    fun `getTrainComponentById returns item successfully`() =
        runBlocking {
            val sampleApiResponse = "{\"id\": 1, \"type\": \"LOCOMOTIVE\", \"description\": \"Sample Description\"}"
            mockWebServer.enqueue(MockResponse().setBody(sampleApiResponse))

            val response = service.getTrainComponentById(1)

            assertNotNull(response)
            assertEquals(1, response.id)
            assertEquals(TrainComponentType.LOCOMOTIVE, response.type)
        }

    @Test
    fun `getTrainComponentsAsFlow emits items successfully`() =
        runBlocking {
            val sampleApiResponse = "[{\"id\": 1, \"type\": \"LOCOMOTIVE\", \"description\": \"Sample Description\"}]"
            mockWebServer.enqueue(MockResponse().setBody(sampleApiResponse))

            val flow = service.getTrainComponentsAsFlow()
            val resultList = flow.toList()

            assertEquals(1, resultList.size)
            assertEquals(TrainComponentType.LOCOMOTIVE, resultList[0][0].type)
        }

    @Test
    fun `getTrainComponents handles empty list response`() =
        runBlocking {
            val emptyListApiResponse = "[]"
            mockWebServer.enqueue(MockResponse().setBody(emptyListApiResponse))

            val response = service.getTrainComponents()

            assertNotNull(response)
            assertTrue(response.isEmpty())
        }
}
