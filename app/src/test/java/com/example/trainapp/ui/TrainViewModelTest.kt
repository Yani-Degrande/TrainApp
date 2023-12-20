package com.example.trainapp.ui

import com.example.trainapp.data.TrainComponent
import com.example.trainapp.ui.trainOverview.TrainUiState
import com.example.trainapp.ui.trainOverview.TrainViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class TrainViewModelTest {
    private lateinit var viewModel: TrainViewModel

    @Before
    fun setUp() {
        viewModel = TrainViewModel()
    }

    @Test
    fun testInitialUiState() {
        val expectedState = TrainUiState(trains = TrainComponent.getAll())
        assertEquals(expectedState, viewModel.trainUiState.value)
    }
}