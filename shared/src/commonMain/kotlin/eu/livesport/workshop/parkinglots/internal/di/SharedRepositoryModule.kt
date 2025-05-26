package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.repository.FavoriteParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.ParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val sharedRepositoryModule: Module =
    module {
        singleOf(::ParkingApi)
        singleOf(::ParkingDataSource)
        singleOf(::ParkingRepositoryImpl) bind ParkingRepository::class
        singleOf(::FavoriteParkingRepositoryImpl) bind FavoriteParkingRepository::class
    }
