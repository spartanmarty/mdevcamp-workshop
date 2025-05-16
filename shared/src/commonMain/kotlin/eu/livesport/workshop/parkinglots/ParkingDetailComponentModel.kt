package eu.livesport.workshop.parkinglots

public data class ParkingDetailComponentModel(
    val name: String,
    val rows: List<RowComponentModel>,
)


public data class RowComponentModel(
    val title: String,
    val value: String,
)

public data class TamItemComponentModel(
    val id: String,
    val name: String,
    val isActive: Boolean,
)

public data class ParkingProhibitionComponentModel(
    val id: String,
    val icon: String,
)