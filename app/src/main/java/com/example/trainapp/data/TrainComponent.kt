package com.example.trainapp.data
import com.example.trainapp.R
data class TrainComponent(
    val type: TrainComponentType,
    val subtype: String,
    val image: Int
)
{
    companion object TrainComponentSampler{
        val sampleTrainComponents = mutableListOf(
            TrainComponent(TrainComponentType.LOCOMOTIVE, "Type 13", R.drawable._3),
            TrainComponent(TrainComponentType.LOCOMOTIVE, "Type 18", R.drawable._8),
            TrainComponent(TrainComponentType.LOCOMOTIVE,"Type 77", R.drawable.hlr77_1),
            TrainComponent(TrainComponentType.LOCOMOTIVE,"Type 11", R.drawable._1),
            TrainComponent(TrainComponentType.LOCOMOTIVE,"Type 27", R.drawable._7),
            TrainComponent(TrainComponentType.CARRIAGE,"I11", R.drawable.i11),
            TrainComponent(TrainComponentType.CARRIAGE,"M4", R.drawable.m4),
            TrainComponent(TrainComponentType.CARRIAGE,"M6", R.drawable.m6),
            TrainComponent(TrainComponentType.TRAINSET,"MS80", R.drawable.ms80),
            TrainComponent(TrainComponentType.TRAINSET,"MS08", R.drawable.ms08),
            TrainComponent(TrainComponentType.TRAINSET,"MS96", R.drawable.ms96),
        )
        val getAll: () -> List<TrainComponent> = {
            val list = mutableListOf<TrainComponent>()
            list.addAll(sampleTrainComponents)
            list
        }
        val getByType: (TrainComponentType) -> List<TrainComponent> = { type ->
            val list = mutableListOf<TrainComponent>()
            list.addAll(sampleTrainComponents.filter { it.type == type })
            list
        }
    }
}