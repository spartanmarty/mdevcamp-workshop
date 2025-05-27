package eu.livesport.workshop.parkinglots

import android.app.Application
import eu.livesport.workshop.parkinglots.di.initKoin
import eu.livesport.workshop.parkinglots.di.modules.applicationModule
import org.koin.android.ext.koin.androidContext

class ParkingLotsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(platformModules = listOf(applicationModule)) {
            androidContext(this@ParkingLotsApplication)
        }
    }
}
