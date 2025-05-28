package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingProhibitions(
    @SerialName("lpg/cng")
    val lpgCng: Boolean?,
    val bus: Boolean?,
    val truck: Boolean?,
    val motorcycle: Boolean?,
    val bicycle: Boolean?,
    val trailer: Boolean?,
)