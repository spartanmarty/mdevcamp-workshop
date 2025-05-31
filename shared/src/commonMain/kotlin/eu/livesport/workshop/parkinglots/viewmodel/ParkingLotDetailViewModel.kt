package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlin.reflect.KClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
public class ParkingLotDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ParkingRepository,
    private val favoriteParkingRepository: FavoriteParkingRepository,
) : ViewModel() {

    private var parkingLotId: String
        get() = savedStateHandle[SAVED_KEY_PARKING_LOT_ID] ?: ""
        set(value) {
            savedStateHandle[SAVED_KEY_PARKING_LOT_ID] = value
            parkingLotIdState.value = value
        }

    private val parkingLotIdState: MutableStateFlow<String> = MutableStateFlow(parkingLotId)
    private val favoriteToggleTrigger: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1)

    public val state: StateFlow<ParkingLotsState> =
        parkingLotIdState.flatMapLatest { id ->
            if (id.isEmpty()) {
                flowOf<ParkingLotsState>(ParkingLotsState.Loading)
            } else {
                favoriteToggleTrigger.transformLatest {
                    val detail = repository.getParkingLotDetail(id)
                    if (detail == null) {
                        emit(ParkingLotsState.Error(type = ParkingLotsState.Error.Type.NO_DATA_FOUND))
                    } else {
                        emit(ParkingLotsState.Data(parkingLots = listOf(detail)))
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ParkingLotsState.Loading,
        )

    public fun loadParkingLotDetail(id: String) {
        parkingLotId = id
        favoriteToggleTrigger.tryEmit(Unit)
    }

    public fun toggleFavorite(parkingLot: ParkingLot) {
        viewModelScope.launch {
            favoriteParkingRepository.toggle(parkingLot)
            favoriteToggleTrigger.emit(Unit)
        }
    }

    public class Factory(
        private val repository: ParkingRepository,
        private val favoriteParkingRepository: FavoriteParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotDetailViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
                repository = repository,
                favoriteParkingRepository = favoriteParkingRepository,
            ) as T
    }
}

private const val SAVED_KEY_PARKING_LOT_ID: String = "parkingLotId"
