package com.example.trainapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.trainapp.R

/**
 * A composable function that provides a pair of lists for use in navigation.
 * It returns a pair consisting of a list of icons and a corresponding list of labels.
 * These can be used, for example, in a navigation bar where each item is represented by an icon and a label.
 *
 * @return A [Pair] where the first element is a list of [Painter] objects representing icons,
 *         and the second element is a list of [String] representing labels.
 */
@Composable
fun pair(): Pair<List<Painter>, List<String>> {
    val trainIcon = painterResource(R.drawable.train_fill0_wght400_grad0_opsz24)
    val groupIcon = painterResource(R.drawable.group_fill0_wght400_grad0_opsz24)
    val homeIcon = painterResource(R.drawable.cottage_fill0_wght400_grad0_opsz24)

    val icons = listOf(homeIcon, trainIcon, groupIcon)
    val items = listOf("Home", "Treinen", "Ploegen")
    return Pair(icons, items)
}
