package eu.livesport.workshop.parkinglots.di

import eu.livesport.workshop.parkinglots.Platform
import org.koin.dsl.module

internal actual val sharedPlatformModule = module {
    single { Platform.ANDROID }
}
