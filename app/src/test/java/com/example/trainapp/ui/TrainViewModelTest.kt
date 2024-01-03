package com.example.trainapp.ui

import com.example.trainapp.fake.FakeTrainRepository
import com.example.trainapp.ui.trainOverview.TrainApiState
import com.example.trainapp.ui.trainOverview.TrainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TrainViewModelTest {
    private lateinit var viewModel: TrainViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TrainViewModel(FakeTrainRepository())
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialUiState() {
        val expectedState = TrainApiState.Success
        assertEquals(expectedState, viewModel.trainApiState)
    }
}
