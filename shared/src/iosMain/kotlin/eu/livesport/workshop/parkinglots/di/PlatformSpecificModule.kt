package eu.livesport.workshop.parkinglots.di

import eu.livesport.workshop.parkinglots.Platform
import org.koin.dsl.module

internal actual val platformSpecificModule = module {
    single { Platform.IOS }
}
