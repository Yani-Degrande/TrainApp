package com.example.trainapp.ui.trainOverview.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainapp.R



@Composable
fun Locomotives() {
    val locomotives = remember { mutableStateOf(com.example.trainapp.data.Train.getAll()) }

    Box {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Locomotieven",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    lineHeight = 12.sp
                )
            )
            LazyRow(
            ) {
                items(locomotives.value.size) {
                    TrainCard(
                        type = locomotives.value[it].type,
                        image = locomotives.value[it].image
                    )
                }
            }
        }
    }
}

