package eu.livesport.workshop.parkinglots.internal.repository.api.mock

import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotAddressApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotPropertiesApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlin.random.Random
import kotlin.random.nextInt

internal class MockParkingApi : ParkingApi {

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel> = PARKING_LOTS

    override suspend fun getParkingLotDetail(id: String): ParkingLotApiModel? =
        PARKING_LOTS.find { it.properties.id == id }
}

private val PARKING_LOTS: List<ParkingLotApiModel> =
    listOf(1, 2, 3, 4, 5).map { id ->
        ParkingLotApiModel(
            ParkingLotPropertiesApiModel(
                id = id.toString(),
                name = "Parking Lot $id",
                address = ParkingLotAddressApiModel(formatted = "Address for Parking Lot $id"),
                capacity = Random.nextInt(100..500),
                covered = Random.nextBoolean(),
            ),
        )
    }
