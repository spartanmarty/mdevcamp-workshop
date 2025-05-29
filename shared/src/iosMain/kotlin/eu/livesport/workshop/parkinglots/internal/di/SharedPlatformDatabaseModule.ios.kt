package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDatabase
import eu.livesport.workshop.parkinglots.internal.database.getFavoriteParkingLotsDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val sharedPlatformDatabaseModule: Module = module {
    single<FavoriteParkingLotsDatabase> { getFavoriteParkingLotsDatabase() }
}
