package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.reflect.KClass

@OptIn(ExperimentalCoroutinesApi::class)
public class ParkingLotsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ParkingRepository,
) : ViewModel() {

    private var selectedFilter: ParkingPolicyFilter
        get() = savedStateHandle[SAVED_KEY_SELECTED_FILTER] ?: ParkingPolicyFilter.NO_FILTER
        set(value) {
            savedStateHandle[SAVED_KEY_SELECTED_FILTER] = value
            selectedFilterState.value = value
        }

    private val selectedFilterState: MutableStateFlow<ParkingPolicyFilter> = MutableStateFlow(selectedFilter)

    public val state: StateFlow<ParkingLotsState> =
        selectedFilterState
            .distinctUntilChangedBy { it }
            .flatMapLatest { filter ->
                flow {
                    emit(ParkingLotsState.Loading)
                    emitAll(
                        repository.getParkingLots(filter)
                            .map { parkingLots ->
                                if (parkingLots.isEmpty()) {
                                    return@map ParkingLotsState.Error(type = ParkingLotsState.Error.Type.NO_DATA_FOUND)
                                }
                                ParkingLotsState.Data(parkingLots = parkingLots)
                            }
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ParkingLotsState.Loading,
            )

    public fun onFilterChange(filter: ParkingPolicyFilter) {
        selectedFilter = filter
    }

    public class Factory(
        private val repository: ParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            ParkingLotsViewModel(
                savedStateHandle = extras.createSavedStateHandle(),
                repository = repository,
            ) as T
    }
}

private const val SAVED_KEY_SELECTED_FILTER: String = "selectedFilter"
