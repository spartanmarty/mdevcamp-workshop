package eu.livesport.workshop.parkinglots.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.core.context.startKoin

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public expect class PlatformModules() {
    public val get: KoinApplication.() -> List<Module>
}

internal expect val platformMainModule: Module

internal fun koinModules(
): List<Module> {
    return listOf(
        platformMainModule,
    )
}

public fun initKoin() {
    startKoin {
        modules(koinModules())
    }
}