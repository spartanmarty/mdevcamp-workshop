package eu.livesport.workshop.parkinglots

import android.app.Application
import eu.livesport.workshop.parkinglots.di.initKoin

class ParkingLotsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}
