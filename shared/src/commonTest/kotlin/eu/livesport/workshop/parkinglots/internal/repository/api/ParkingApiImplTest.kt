package eu.livesport.workshop.parkinglots.internal.repository.api

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.repository.api.model.ParkingLotApiModelMocks
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class ParkingApiImplTest {

    private val networkExecutorMock: NetworkExecutor = mock()

    private val testedClass: ParkingApiImpl = ParkingApiImpl(networkExecutorMock)

    @Test
    fun testGetParkingLots_success() =
        testGetParkingLotsInternal(
            successApiResponse = true,
            expectedResult = ParkingLotApiModelMocks.apiResponse.parkingLots,
        )

    @Test
    fun testGetParkingLots_failed() =
        testGetParkingLotsInternal(
            successApiResponse = false,
            expectedResult = emptyList(),
        )

    private fun testGetParkingLotsInternal(
        successApiResponse: Boolean,
        expectedResult: List<ParkingLotApiModel>,
    ) = runTest {
        val response = if (successApiResponse) {
            Result.success(ParkingLotApiModelMocks.apiResponse)
        } else {
            Result.failure(ConnectTimeoutException("Connection timed out"))
        }

        everySuspend { networkExecutorMock.get(URL_PARKING_LOTS, ParkingApiModel::class) } returns response

        val result = testedClass.getParkingLots(ParkingPolicyFilter.NO_FILTER)
        assertEquals(expected = expectedResult, actual = result)
    }
}

private const val URL_PARKING_LOTS: String = "https://api.golemio.cz/v3/parking?limit=10"
