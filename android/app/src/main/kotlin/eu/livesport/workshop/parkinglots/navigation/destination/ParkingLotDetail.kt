package eu.livesport.workshop.parkinglots.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
data class ParkingLotDetail(
    val parkingLotId: String,
)