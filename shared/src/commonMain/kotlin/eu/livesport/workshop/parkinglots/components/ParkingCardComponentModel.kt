package eu.livesport.workshop.parkinglots.components

public data class ParkingCardComponentModel(
    val id: String,
    val title: String,
    val rows: List<RowComponentModel>,
)
