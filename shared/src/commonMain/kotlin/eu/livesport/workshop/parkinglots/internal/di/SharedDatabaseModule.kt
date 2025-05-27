package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDao
import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal val sharedDatabaseModule: Module = module {
    includes(sharedPlatformDatabaseModule)
    single<FavoriteParkingLotsDao> {
        get<FavoriteParkingLotsDatabase>().favoriteParkingLotsDao()
    }
}

internal expect val sharedPlatformDatabaseModule: Module