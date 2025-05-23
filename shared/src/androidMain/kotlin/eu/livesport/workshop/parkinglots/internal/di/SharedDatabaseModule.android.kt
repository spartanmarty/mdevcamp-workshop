package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.database.getFavoriteParkingLotsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformDatabaseModule: Module = module {
    single { getFavoriteParkingLotsDatabase(context = androidContext()) }
}