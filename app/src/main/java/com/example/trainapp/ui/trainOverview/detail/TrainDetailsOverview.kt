package com.example.trainapp.ui.trainOverview.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.trainapp.R
import com.example.trainapp.ui.components.ErrorMessage

/**
 * A composable function that displays the details of a specific train component.
 * It observes the UI state and API state from the ViewModel and displays different content based on the current state,
 * such as loading indicators, error messages, or detailed information about a train component.
 *
 * @param innerPadding The padding to apply to the content. Useful for adjusting layout based on navigation components.
 * @param trainId The unique identifier of the train component for which details are being displayed.
 * @param viewModel The ViewModel that provides the data and state for this screen.
 */
@Composable
fun TrainDetailsOverview(
    innerPadding: PaddingValues,
    trainId: Int,
    viewModel: TrainDetailViewModel = viewModel(factory = TrainDetailViewModel.Factory(trainId)),
) {
    val trainDetailUiState by viewModel.uiListState.collectAsState()

    // Use the Api State
    val trainApiState = viewModel.trainDetailApiState
    Box(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        // Ensures the Box fills the entire available space
        contentAlignment = Alignment.Center,
    ) {
        when (trainApiState) {
            is TrainDetailApiState.Error -> {
                ErrorMessage(errorMessage = "Oops, er is iets misgegaan! Probeer het opnieuw.", onRetry = { viewModel.retry() })
            }
            is TrainDetailApiState.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is TrainDetailApiState.Success -> {
                val train = trainDetailUiState.trainComponent
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()) // Make the column scrollable
                            .padding(16.dp), // Add padding around the Column
                ) {
                    AsyncImage(
                        model = train?.descriptionImage,
                        contentDescription = train?.subtype,
                        error = painterResource(R.drawable.ic_broken_image),
                        placeholder = painterResource(R.drawable.loading_img),
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(16.dp)),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    train?.subtype?.let {
                        Text(
                            text = it,
                            style =
                                MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                ),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    train?.description?.let {
                        Text(
                            text = it,
                            style =
                                MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black.copy(alpha = 0.75f),
                                ),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }
    }
}
