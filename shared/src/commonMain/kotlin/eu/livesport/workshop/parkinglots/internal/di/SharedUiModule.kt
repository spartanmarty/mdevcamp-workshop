package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.ui.ProhibitionIconResolver
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val sharedUIModule = module {
    factoryOf(::ProhibitionIconResolver)
}