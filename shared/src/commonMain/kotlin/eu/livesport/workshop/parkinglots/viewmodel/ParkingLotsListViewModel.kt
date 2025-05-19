package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public class ParkingLotsListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ParkingRepository,
) : ViewModel() {

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    public val state: StateFlow<State> = _state.asStateFlow()

    public fun loadParkingLots(forceRefresh: Boolean = false) {
        val skipLoading: Boolean =
            _state.value is State.Loading ||
                (_state.value is State.Data && !forceRefresh)

        if (skipLoading) {
            return
        }

        _state.value = State.Loading
        viewModelScope.launch {
            _state.value = State.Data(parkingLots = repository.getParkingLots())
        }
    }

    public sealed class State {
        public data object Initial : State()
        public data object Loading : State()
        public data class Data(val parkingLots: List<ParkingLot>) : State()
    }

    public class Factory(
        private val repository: ParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotsListViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
                repository = repository,
            ) as T
    }
}
