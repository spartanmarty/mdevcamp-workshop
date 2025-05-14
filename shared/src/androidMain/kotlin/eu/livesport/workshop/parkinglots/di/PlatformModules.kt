package eu.livesport.workshop.parkinglots.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class PlatformModules(
    public actual val get: KoinApplication.() -> List<Module>,
) {
    public actual constructor() : this({ emptyList() })
}
