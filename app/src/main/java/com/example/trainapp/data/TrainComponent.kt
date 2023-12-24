package com.example.trainapp.data
import com.example.trainapp.R
data class TrainComponent(
    val id: Int,
    val type: TrainComponentType,
    val subtype: String,
    val image: String //Moet verangen worden door string
)
{
    companion object TrainComponentSampler{
        val sampleTrainComponents = mutableListOf(
            TrainComponent(1, TrainComponentType.LOCOMOTIVE, "Type 13", "https://1drv.ms/i/s!AvjKzZp_0UVAgwvttmxzmUMrmur7?e=En7t97"),
            TrainComponent(2, TrainComponentType.LOCOMOTIVE, "Type 18", "https://1drv.ms/i/s!AvjKzZp_0UVAgn22GroimlRn8OoR?e=RLgNAp"),
            TrainComponent(3, TrainComponentType.LOCOMOTIVE,"Type 77", "https://1drv.ms/i/s!AvjKzZp_0UVAgwD-sU5TnQGgIo8c?e=Dk8qb2"),
            TrainComponent(4, TrainComponentType.LOCOMOTIVE,"Type 11", "https://1drv.ms/i/s!AvjKzZp_0UVAgwkdcYdpCbRZAmJY?e=RfGUGU"),
            TrainComponent(5, TrainComponentType.LOCOMOTIVE,"Type 27", "https://1drv.ms/i/s!AvjKzZp_0UVAgn79YHS4KBPavomz?e=H8DasN"),
            TrainComponent(6, TrainComponentType.CARRIAGE,"I11", "https://1drv.ms/i/s!AvjKzZp_0UVAgwFXSex6fhoptcvX?e=IVnKYb"),
            TrainComponent(7, TrainComponentType.CARRIAGE,"M4", "https://1drv.ms/i/s!AvjKzZp_0UVAgwRaxpzTQ3n523CZ?e=XTghqG"),
            TrainComponent(8, TrainComponentType.CARRIAGE,"M6","https://1drv.ms/i/s!AvjKzZp_0UVAgwViKad-JvBikMTw?e=0BaClM"),
            TrainComponent(9, TrainComponentType.TRAINSET,"MS80", "https://1drv.ms/i/s!AvjKzZp_0UVAgwYAorj1CaibXz2H?e=s58G38"),
            TrainComponent(10, TrainComponentType.TRAINSET,"MS08", "https://1drv.ms/i/s!AvjKzZp_0UVAgwegFD2vAKlZuLoB?e=ph1XIc"),
            TrainComponent(11, TrainComponentType.TRAINSET,"MS96", "https://1drv.ms/i/s!AvjKzZp_0UVAgwhcGsp2E3p-Bbgm?e=3gMJFq"),
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