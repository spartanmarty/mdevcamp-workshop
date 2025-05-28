package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import kotlin.reflect.KClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
public class ParkingLotDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ParkingRepository,
) : ViewModel() {

    private var parkingLotId: String
        get() = savedStateHandle[SAVED_KEY_PARKING_LOT_ID] ?: ""
        set(value) {
            savedStateHandle[SAVED_KEY_PARKING_LOT_ID] = value
            parkingLotIdState.value = value
        }

    private val parkingLotIdState: MutableStateFlow<String> = MutableStateFlow(parkingLotId)

    public val state: StateFlow<State> =
        parkingLotIdState.flatMapLatest { id ->
            if (id.isEmpty()) {
                flowOf<State>(State.Loading)
            } else {
                flow {
                    val detail = repository.getParkingLotDetail(id)
                    if (detail == null) {
                        emit(State.Error(type = State.Error.Type.NO_DATA_FOUND))
                    } else {
                        emit(State.Data(parkingLots = listOf(detail)))
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = State.Loading,
        )

    public fun loadParkingLotDetail(id: String) {
        parkingLotId = id
    }

    public class Factory(
        private val repository: ParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotDetailViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
                repository = repository,
            ) as T
    }
}

private const val SAVED_KEY_PARKING_LOT_ID: String = "parkingLotId"
