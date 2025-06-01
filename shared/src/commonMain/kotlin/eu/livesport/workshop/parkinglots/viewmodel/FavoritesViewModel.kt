package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

public class FavoritesViewModel(
    private val favoriteParkingRepository: FavoriteParkingRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<ParkingLotsState> = MutableStateFlow(ParkingLotsState.Loading)
    public val state: StateFlow<ParkingLotsState> = _state.asStateFlow()

    public fun loadFavoriteParkingLots() {
        if (_state.value is ParkingLotsState.Data) {
            return
        }

        _state.value = ParkingLotsState.Loading
        viewModelScope.launch {
            favoriteParkingRepository.getFavoriteParkingLots().collect { parkingLots ->
                if (parkingLots.isEmpty()) {
                    _state.value = ParkingLotsState.Error(type = ParkingLotsState.Error.Type.NO_DATA_FOUND)
                    return@collect
                }
                _state.value = ParkingLotsState.Data(parkingLots = parkingLots)
            }
        }
    }

    public class Factory(
        private val favoriteParkingRepository: FavoriteParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            FavoritesViewModel(favoriteParkingRepository = favoriteParkingRepository) as T
    }
}
