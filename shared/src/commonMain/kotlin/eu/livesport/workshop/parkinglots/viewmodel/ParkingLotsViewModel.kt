package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public class ParkingLotsViewModel(
    private val repository: ParkingRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    public val state: StateFlow<State> = _state.asStateFlow()

    public fun loadParkingLots(filters: ParkingPolicyFilter) {
        _state.value = State.Loading
        viewModelScope.launch {
            repository.getParkingLots(filters).collect { parkingLots ->
                _state.value = State.Data(parkingLots = parkingLots)
            }
        }
    }

    public class Factory(
        private val repository: ParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotsViewModel(repository = repository) as T
    }
}
