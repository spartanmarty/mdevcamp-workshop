package eu.livesport.workshop.parkinglots.di

import eu.livesport.workshop.parkinglots.internal.di.sharedDatabaseModule
import eu.livesport.workshop.parkinglots.internal.di.sharedNetworkModule
import eu.livesport.workshop.parkinglots.internal.di.sharedRepositoryModule
import eu.livesport.workshop.parkinglots.internal.di.sharedViewModelsModule
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

public fun initKoin(
    platformModules: List<Module> = emptyList(),
    appDeclaration: KoinApplication.() -> Unit = {},
) {
    startKoin {
        appDeclaration()
        allowOverride(false)
        modules(platformModules + sharedInternalModule)
    }
}

internal val sharedInternalModule: Module =
    module {
        includes(
            sharedNetworkModule,
            sharedPlatformModule,
            sharedRepositoryModule,
            sharedViewModelsModule,
            sharedDatabaseModule,
        )
    }

internal expect val sharedPlatformModule: Module

public object KoinHelper : KoinComponent {
    public fun getFavoriteParkingRepository(): FavoriteParkingRepository = get()
    public fun getParkingRepository(): ParkingRepository = get()
}
