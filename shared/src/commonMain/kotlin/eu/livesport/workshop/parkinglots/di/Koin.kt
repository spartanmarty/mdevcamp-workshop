package eu.livesport.workshop.parkinglots.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

public fun initKoin(platformModules: List<Module> = emptyList()) {
    startKoin {
        allowOverride(false)
        modules(platformModules)
        sharedInternalModule
    }
}

internal val sharedInternalModule = module {
    includes(sharedPlatformModule)

    // Include another internal modules (e.g. network, database, etc.)
}

internal expect val sharedPlatformModule: Module
