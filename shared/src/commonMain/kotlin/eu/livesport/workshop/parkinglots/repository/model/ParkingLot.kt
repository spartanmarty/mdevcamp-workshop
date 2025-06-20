package eu.livesport.workshop.parkinglots.repository.model

public data class ParkingLot(
    val id: String,
    val name: String,
    val address: String?,
    val capacity: Int,
    val covered: Boolean?,
    val isFavorite: Boolean = false,
    val prohibitions: List<ParkingProhibitions>,
)
