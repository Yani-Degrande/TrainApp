package com.example.trainapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.trainapp.R

/**
 * A custom composable function that creates a top app bar with a logo and title.
 * The app bar can optionally include a back button.
 *
 * This function uses the ExperimentalMaterial3Api class, indicating that it relies on an API
 * that is experimental and may change in the future.
 *
 * @param modifier A [Modifier] applied to the app bar, used to adjust its styling or layout.
 * @param title The resource ID of the title string to be displayed in the app bar.
 * @param showBackButton A boolean value indicating whether to show a back button on the app bar.
 * @param onBackButtonPressed A lambda function that is executed when the back button is pressed.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    modifier: Modifier = Modifier,@StringRes title: Int,showBackButton: Boolean = false, onBackButtonPressed: ()-> Unit,
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
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleMedium, // Use a predefined typography style
                    color = MaterialTheme.colorScheme.onSurface // Use a color that contrasts well with the app bar background
                )
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackButtonPressed() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
    )
}