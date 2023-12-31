package com.example.trainapp.ui.trainOverview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainapp.model.TrainComponentType
import com.example.trainapp.ui.components.ErrorMessage
import com.example.trainapp.ui.trainOverview.components.TrainComponentList

/**
 * A composable function that displays the overview of train components.
 * It observes various UI states and displays different content based on the current state,
 * such as loading indicators, error messages, or a list of train components.
 *
 * @param innerPadding The padding to apply to the content. Useful for adjusting layout based on navigation components.
 * @param viewModel The ViewModel that provides the data and state for this screen.
 * @param onTrainComponentClick A lambda function that is called when a train component is clicked.
 */
@Composable
fun TrainOverview(
    innerPadding: PaddingValues,
    viewModel: TrainViewModel = viewModel(factory = TrainViewModel.Factory),
    onTrainComponentClick: (Int) -> Unit,
) {
    val trainUiState by viewModel.uiState.collectAsState()
    val uiListState by viewModel.uiListState.collectAsState()

    // Use the Api State
    val trainApiState = viewModel.trainApiState
    // use the workerstate
    val workerState by viewModel.wifiWorkerState.collectAsState()

    Box(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
        ) {
            when (workerState.workerInfo?.state) {
                null ->
                    item {
                        ErrorMessage(
                            errorMessage = "Er is iets misgegaan met de wifi verbinding. Probeer het opnieuw.",
                            onRetry = { viewModel.retry() },
                        )
                    }

                else ->
                    when (trainApiState) {
                        is TrainApiState.Error -> {
                            item {
                                ErrorMessage(
                                    errorMessage = "Oops, er is iets misgegaan! Probeer het opnieuw.",
                                    onRetry = { viewModel.retry() },
                                )
                            }
                        }

                        is TrainApiState.Loading -> {
                            item {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is TrainApiState.Success -> {
                            val locomotives =
                                uiListState.trainComponentList.filter { it.type == TrainComponentType.LOCOMOTIVE }
                            val carriages =
                                uiListState.trainComponentList.filter { it.type == TrainComponentType.CARRIAGE }
                            val trainsets =
                                uiListState.trainComponentList.filter { it.type == TrainComponentType.TRAINSET }

                            // Check if all lists are empty
                            if (locomotives.isEmpty() && carriages.isEmpty() && trainsets.isEmpty()) {
                                item {
                                    ErrorMessage(
                                        errorMessage = "Geen treinonderdelen beschikbaar. Probeer het later opnieuw.",
                                        onRetry = { viewModel.retry() },
                                    )
                                }
                            } else {
                                // Existing logic to display lists
                                item {
                                    TrainComponentList(
                                        locomotives,
                                        "Locomotieven",
                                        onTrainComponentClick,
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    TrainComponentList(carriages, "Rijtuigen", onTrainComponentClick)
                                    Spacer(modifier = Modifier.height(16.dp))
                                    TrainComponentList(trainsets, "Treinstellen", onTrainComponentClick)
                                }
                            }
                        }
                    }
            }
        }
    }
}
