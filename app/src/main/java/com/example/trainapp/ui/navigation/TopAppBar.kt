package com.example.trainapp.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trainapp.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    topAppBarTitle: String,
    modifier: Modifier = Modifier,
) {
    val trainIcon = painterResource(R.drawable.logo)
    androidx.compose.material3.TopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = trainIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp) //
                        .padding(end = 16.dp)
                )
                Text(
                    text = topAppBarTitle,
                    style = MaterialTheme.typography.titleMedium, // Use a predefined typography style
                    color = MaterialTheme.colorScheme.onSurface // Use a color that contrasts well with the app bar background
                )
            }
        },
    )
}