package com.example.trainapp.ui.trainOverview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainapp.data.TrainComponent


@Composable
fun TrainComponentList(trainComponents : List<TrainComponent>, trainComponentType: String, onTrainComponentClick: (Int) -> Unit) {

    Box {
        Column() {
            Text(
                text = trainComponentType,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    lineHeight = 12.sp
                )
            )
            LazyRow(
            ) {
                items(trainComponents.size) {
                    TrainCard(
                        trainId = trainComponents[it].id,
                        type = trainComponents[it].subtype,
                        image = trainComponents[it].image,
                        onClick = onTrainComponentClick
                    )
                }
            }
        }
    }
}

