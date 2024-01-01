package com.example.trainapp.ui.navigation

import androidx.annotation.StringRes
import com.example.trainapp.R

/**
 * Destinations used in the app.
 * Each destination is paired with a string resource id.
 * @param title string resource id
 */
enum class Destinations (@StringRes val title: Int) {
    Start(title = R.string.train_app_title),
    Train(title = R.string.trains),
    Teams(title = R.string.teams),
    TrainDetail(title = R.string.train_detail)
}