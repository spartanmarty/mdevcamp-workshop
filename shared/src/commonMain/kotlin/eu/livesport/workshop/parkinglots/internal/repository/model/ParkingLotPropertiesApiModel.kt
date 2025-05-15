package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotPropertiesApiModel(
    val id: String,
    val name: String,
    val capacity: Int,
)
