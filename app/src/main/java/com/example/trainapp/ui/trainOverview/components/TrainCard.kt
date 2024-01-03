package com.example.trainapp.ui.trainOverview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.trainapp.R

/**
 * A composable function that creates a card representation of a train component.
 * The card displays an image and the type of the train component. It is clickable and triggers an action when clicked.
 *
 * @param trainId The unique identifier of the train component.
 * @param type The type of the train component, displayed as text on the card.
 * @param image The URL or resource identifier of the image to be displayed on the card.
 * @param onClick A lambda function that is called with the trainId when the card is clicked.
 */
@Composable
fun TrainCard(
    trainId: Int,
    type: String,
    image: String,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .clickable { onClick(trainId) }
                .testTag("TrainCard-$trainId")
                .height(IntrinsicSize.Min) // Adjust the height to fit the content
                .width(180.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize() // Fill the Box
                    .padding(8.dp),
            // Add padding inside the Column
            horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
            verticalArrangement = Arrangement.spacedBy(8.dp), // Space the children vertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = type,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp)),
            )
            Text(
                text = type,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier =
                    Modifier
                        .fillMaxWidth(),
                style =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                    ),
            )
        }
    }
}
