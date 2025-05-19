package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotPropertiesApiModel(
    val id: String,
    val name: String,
    val address: ParkingLotAddressApiModel?,
    val capacity: Int,
    val covered: Boolean?,
    // TODO: Parse prohibitions from API response
    //  val prohibitions: ...,
)
