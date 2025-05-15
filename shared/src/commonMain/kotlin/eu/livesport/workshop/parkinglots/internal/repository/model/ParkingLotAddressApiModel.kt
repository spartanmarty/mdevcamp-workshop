package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotAddressApiModel(
    @SerialName("address_formatted")
    val formatted: String,
)
