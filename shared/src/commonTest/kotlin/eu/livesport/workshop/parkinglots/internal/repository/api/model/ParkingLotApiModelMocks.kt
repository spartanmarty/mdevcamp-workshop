package eu.livesport.workshop.parkinglots.internal.repository.api.model

import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotAddressApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotPropertiesApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingProhibitions

internal object ParkingLotApiModelMocks {

    val apiResponse: ParkingApiModel =
        ParkingApiModel(
            parkingLots = listOf(
                ParkingLotApiModel(
                    properties = ParkingLotPropertiesApiModel(
                        id = "1",
                        name = "name",
                        address = ParkingLotAddressApiModel(formatted = "address"),
                        capacity = 500,
                        covered = true,
                        parkingProhibitions = ParkingProhibitions(
                            lpgCng = true,
                            bus = false,
                            truck = true,
                            motorcycle = false,
                            bicycle = true,
                            trailer = false,
                        ),
                    ),
                ),
            ),
        )
}
