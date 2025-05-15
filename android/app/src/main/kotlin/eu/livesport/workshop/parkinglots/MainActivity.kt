package eu.livesport.workshop.parkinglots

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import eu.livesport.workshop.parkinglots.repository.ParkingLotDataRepository
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FOR TESTING PURPOSES ONLY
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val parkingLots = ParkingLotDataRepository.INSTANCE.getParkingLots()
                Log.d("MainActivity", "Parking lots: $parkingLots")
            }
        }
    }
}
