package eu.livesport.workshop.parkinglots.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

public actual class PlatformModules(
    public actual val get: KoinApplication.() -> List<Module>,
) {
    public actual constructor() : this({ emptyList() })
}
