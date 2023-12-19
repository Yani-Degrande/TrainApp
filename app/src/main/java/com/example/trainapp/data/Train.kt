package com.example.trainapp.data

import com.example.trainapp.R

data class Train(var type: String, var image: Int) {
    companion object TrainSampler{
        val sampleTrains = mutableListOf(
            Train("Type 13", R.drawable._3),
            Train("Type 18", R.drawable._8),
            Train("Type 77", R.drawable.hlr77_1),
        )
        val getAll: () -> List<Train> = {
            val list = mutableListOf<Train>()
            list.addAll(sampleTrains)
            list
        }
    }
}
