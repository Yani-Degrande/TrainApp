package com.example.trainapp.ui.teamOverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainapp.R

/**
 * A composable function that displays the team overview screen.
 * This screen currently shows a placeholder message and image indicating that the content is not yet available.
 *
 * @param innerPadding The padding to apply to the content. Useful for adjusting layout based on navigation components.
 */
@Composable
fun TeamOverview(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp) // Add padding around the Column
                .wrapContentSize() // Wrap content instead of filling max size
        ){
            Image(
                painter = painterResource(id = R.drawable.moderntrain_cartoon),
                contentDescription = "Modern train",
                modifier = Modifier
                    .size(300.dp) // Example fixed size, adjust as needed
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Oops!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = "Nog niets te zien hier!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

        }
    }
}