package eu.livesport.workshop.parkinglots.di

import androidx.lifecycle.SavedStateHandle
import eu.livesport.workshop.parkinglots.Platform
import eu.livesport.workshop.parkinglots.viewmodel.FavoritesViewModel
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotDetailViewModel
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel
import org.koin.core.module.factory
import org.koin.dsl.module

internal actual val sharedPlatformModule = module {
    single { Platform.IOS }
    factory {
        ParkingLotsViewModel(
            savedStateHandle = SavedStateHandle(emptyMap()),
            repository = get()
        )
    }
    factory {
        ParkingLotDetailViewModel(
            savedStateHandle = SavedStateHandle(emptyMap()),
            repository = get(),
            favoriteParkingRepository = get()
        )
    }
    factory {
        FavoritesViewModel(
            favoriteParkingRepository = get()
        )
    }
}
