package eu.livesport.workshop.parkinglots.data

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

val favoriteItemsMock: List<ParkingLot> =
    listOf(
        ParkingLot(
            id = "park_1234",
            name = "Parking 4",
            address = "Žitná 1234/987, Praha 2",
            capacity = 200,
            covered = true,
        ),
    )

data class ParkingDetailModel(
    val title: String,
    val capacity: Int,
    val address: String,
    val covered: Boolean,
    val prohibitions: List<String>,
    val description: String,
    val type: String,
    val parkingPolicy: String,
)

val parkingDetailMock = ParkingDetailModel(
    title = "Areál Gayerova kasárna",
    capacity = 60,
    address = "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
    covered = true,
    prohibitions = listOf("No loud music", "No littering"),
    description = "This is a detailed description of the parking lot. It includes information about the location, capacity, and other relevant details.",
    type = "Public Parking Lot",
    parkingPolicy = "Free parking for up to 2 hours, then paid parking applies.",
)
