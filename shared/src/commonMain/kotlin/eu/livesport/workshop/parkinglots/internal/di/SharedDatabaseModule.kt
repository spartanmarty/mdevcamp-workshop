package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDao
import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val platformDatabaseModule: Module

internal val sharedDatabaseModule: Module = module {
    single<FavoriteParkingLotsDao> {
        get<FavoriteParkingLotsDatabase>().favoriteParkingLotsDao()
    }
}