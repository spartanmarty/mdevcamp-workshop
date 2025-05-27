package eu.livesport.workshop.parkinglots.di

import eu.livesport.workshop.parkinglots.build.BuildConfigProvider
import org.koin.core.module.Module
import org.koin.dsl.module

public object ApplicationModule {

    public fun createKoinModule(buildConfigProvider: () -> BuildConfigProvider): Module =
        module {
            factory<BuildConfigProvider> { buildConfigProvider() }
        }
}
