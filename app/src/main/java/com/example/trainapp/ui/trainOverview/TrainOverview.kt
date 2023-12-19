package com.example.trainapp.ui.trainOverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trainapp.ui.trainOverview.components.Locomotives

@Composable
fun TrainOverview(innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding)) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Locomotives()
            }
        }
    }
}