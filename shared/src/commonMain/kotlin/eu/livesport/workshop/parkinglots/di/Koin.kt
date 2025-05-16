package eu.livesport.workshop.parkinglots.di

import org.koin.core.module.Module
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal val platformModule = module {
    // Register module
    single { "Default module" }

    // Include another internal module
    includes(platformSpecificModule)
}

internal expect val platformSpecificModule: Module

public fun initKoin() {
    startKoin {
        platformModule
        // Add more modules if needed
    }
}