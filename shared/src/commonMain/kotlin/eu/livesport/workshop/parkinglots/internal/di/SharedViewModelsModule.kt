package eu.livesport.workshop.parkinglots.internal.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotDetailViewModel.Factory as ParkingLotDetailViewModelFactory
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel.Factory as ParkingLotsListViewModelFactory

internal val sharedViewModelsModule: Module =
    module {
        factoryOf(::ParkingLotDetailViewModelFactory)
        factoryOf(::ParkingLotsListViewModelFactory)
    }
