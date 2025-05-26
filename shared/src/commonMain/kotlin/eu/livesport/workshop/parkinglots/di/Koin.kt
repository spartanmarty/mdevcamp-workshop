package eu.livesport.workshop.parkinglots.di

import eu.livesport.workshop.parkinglots.internal.di.sharedDatabaseModule
import eu.livesport.workshop.parkinglots.internal.di.sharedNetworkModule
import eu.livesport.workshop.parkinglots.internal.di.sharedRepositoryModule
import eu.livesport.workshop.parkinglots.internal.di.sharedViewModelsModule
import org.koin.core.KoinApplication
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
