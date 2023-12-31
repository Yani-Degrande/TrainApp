package com.example.trainapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.trainapp.R

@Composable
fun pair(): Pair<List<Painter>, List<String>> {
    val trainIcon = painterResource(R.drawable.train_fill0_wght400_grad0_opsz24)
    val groupIcon = painterResource(R.drawable.group_fill0_wght400_grad0_opsz24)
    val homeIcon = painterResource(R.drawable.cottage_fill0_wght400_grad0_opsz24)

    val icons = listOf(homeIcon, trainIcon, groupIcon)
    val items = listOf("Home", "Treinen", "Ploegen")
    return Pair(icons, items)
}
