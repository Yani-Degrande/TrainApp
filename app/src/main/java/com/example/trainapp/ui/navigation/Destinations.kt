package com.example.trainapp.ui.navigation

import androidx.annotation.StringRes
import com.example.trainapp.R

enum class Destinations (@StringRes val title: Int) {
    Start(title = R.string.train_app_title),
    Teams(title = R.string.teams),
}