package eu.livesport.workshop.parkinglots.di.modules

import eu.livesport.workshop.parkinglots.build.BuildConfigProvider
import eu.livesport.workshop.parkinglots.build.BuildConfigProviderImpl
import eu.livesport.workshop.parkinglots.ui.Icons
import eu.livesport.workshop.parkinglots.ui.common.IconsImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val applicationModule: Module =
    module {
        factoryOf(::BuildConfigProviderImpl) bind BuildConfigProvider::class
        factoryOf(::IconsImpl) bind Icons::class
    }
