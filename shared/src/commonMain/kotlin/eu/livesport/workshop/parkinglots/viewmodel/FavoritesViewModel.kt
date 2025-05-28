package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

public class FavoritesViewModel() : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Error(type = State.Error.Type.NO_DATA_FOUND))
    public val state: StateFlow<State> = _state.asStateFlow()

    public fun loadFavoriteParkingLots(): Unit = Unit // TODO: Implement.
}
