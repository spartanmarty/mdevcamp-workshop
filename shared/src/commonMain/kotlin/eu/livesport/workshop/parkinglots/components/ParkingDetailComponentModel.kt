package eu.livesport.workshop.parkinglots.components

public data class ParkingDetailComponentModel(
    val id: String,
    val title: String,
    val rows: List<RowComponentModel>,
    val prohibitions: List<ProhibitionComponentModel>,
)
