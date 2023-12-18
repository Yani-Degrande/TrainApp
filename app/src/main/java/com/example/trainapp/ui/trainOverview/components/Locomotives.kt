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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainapp.R

@Composable
fun Locomotives() {
    val type13  = painterResource(R.drawable._3)
    val type18 = painterResource(R.drawable._8)
    val type77 = painterResource(R.drawable.hlr77_1)

    val images = listOf(type13, type18, type77)
    val types = listOf("Type 13", "Type 18", "Type 77")

    Box {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Locomotives",
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
                items(images.size) {
                    Train(type = types[it], image = images[it])
                }
            }
        }
    }
}