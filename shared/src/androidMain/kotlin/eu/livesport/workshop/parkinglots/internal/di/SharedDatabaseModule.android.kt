package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.database.getFavoriteParkingLotsDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

internal actual val sharedDatabaseModule: Module = module {
    single { getFavoriteParkingLotsDatabase(context = androidContext()) }
}