package eu.livesport.workshop.parkinglots.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

public class FavoritesViewModel(
    private val favoriteParkingRepository: FavoriteParkingRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<ParkingLotsState> = MutableStateFlow(ParkingLotsState.Error(type = ParkingLotsState.Error.Type.NO_DATA_FOUND))
    public val state: StateFlow<ParkingLotsState> = _state.asStateFlow()

    public fun loadFavoriteParkingLots(): Unit = Unit // TODO: Implement.

    public class Factory(
        private val favoriteParkingRepository: FavoriteParkingRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
            FavoritesViewModel(favoriteParkingRepository = favoriteParkingRepository) as T
    }
}
