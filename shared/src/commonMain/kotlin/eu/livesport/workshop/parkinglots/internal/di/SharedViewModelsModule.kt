package eu.livesport.workshop.parkinglots.internal.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsListViewModel.Factory as ParkingLotsListViewModelFactory

internal val sharedViewModelsModule: Module =
    module {
        factoryOf(::ParkingLotsListViewModelFactory)
    }
