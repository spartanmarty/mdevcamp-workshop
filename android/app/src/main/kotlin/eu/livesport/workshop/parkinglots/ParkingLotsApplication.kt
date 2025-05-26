package eu.livesport.workshop.parkinglots

import android.app.Application
import eu.livesport.workshop.parkinglots.di.initKoin
import org.koin.android.ext.koin.androidContext

class ParkingLotsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@ParkingLotsApplication)
        }
    }
}
