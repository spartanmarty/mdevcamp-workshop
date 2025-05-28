package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingProhibitions
import kotlin.reflect.KClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
public class ParkingLotDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var parkingLotId: String
        get() = savedStateHandle[SAVED_KEY_PARKING_LOT_ID] ?: ""
        set(value) {
            savedStateHandle[SAVED_KEY_PARKING_LOT_ID] = value
        }

    public val state: StateFlow<State> =
        MutableStateFlow(
            State.Data(
                listOf(
                    ParkingLot(
                        id = "123",
                        name = "Dummy Parking Lot",
                        address = null,
                        capacity = 0,
                        covered = false,
                        isFavorite = false,
                        prohibitions = listOf(ParkingProhibitions.LPG_CNG, ParkingProhibitions.TRUCK),
                    )
                )
            )
        )

    public fun loadParkingLotDetail(id: String) {
        parkingLotId = id
    }

    public class Factory() : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotDetailViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
            ) as T
    }
}

private const val SAVED_KEY_PARKING_LOT_ID: String = "parkingLotId"
