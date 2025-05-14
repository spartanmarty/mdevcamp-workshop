package eu.livesport.workshop.parkinglots.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

public val iosPlatformModules: KoinApplication.() -> List<Module> = {
    listOf()
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class PlatformModules(platformInputModules: List<Module>) {
    public actual val get: KoinApplication.() -> List<Module> = {
        iosPlatformModules() + platformInputModules
    }
    public actual constructor() : this(emptyList())
}
