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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

public class ParkingLotsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ParkingRepository,
    private val favoriteParkingRepository: FavoriteParkingRepository
) : ViewModel() {

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    public val state: StateFlow<State> = _state.asStateFlow()

    public fun loadParkingLots(forceRefresh: Boolean = false) {
        if (_state.value is State.Data && !forceRefresh) {
            return
        }

        _state.value = State.Loading
        viewModelScope.launch {
            repository.getParkingLots().collect { parkingLots ->
                _state.value = State.Data(parkingLots = parkingLots)
            }
        }
    }

    public fun toggleFavorite(parkingLot: ParkingLot) {
        viewModelScope.launch {
            favoriteParkingRepository.toggle(parkingLot)
        }
    }

    public fun getParkingLot(id: String): Flow<ParkingLot?> {
        return state
            .filterIsInstance<State.Data>()
            .map {
                it.parkingLots.find { lot -> lot.id == id }
            }
    }

    public class Factory(
        private val repository: ParkingRepository,
        private val favoriteParkingRepository: FavoriteParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotsViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
                repository = repository,
                favoriteParkingRepository = favoriteParkingRepository
            ) as T
    }
}
