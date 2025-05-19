package eu.livesport.workshop.parkinglots

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import eu.livesport.workshop.parkinglots.ui.ParkingLotsApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingLotsApp()
        }
    }
}
