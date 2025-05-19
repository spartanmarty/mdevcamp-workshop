package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutorImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val sharedNetworkModule: Module =
    module {
        singleOf(::NetworkExecutorImpl) bind NetworkExecutor::class
    }
