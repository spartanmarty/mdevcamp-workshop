package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsListViewModel
import org.koin.mp.KoinPlatform

@Composable
fun ParkingLotsListScreen(
    viewModel: ParkingLotsListViewModel = createViewModel(),
    onItemClick: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.loadParkingLots()
    }

    val state: ParkingLotsListViewModel.State by viewModel.state.collectAsStateWithLifecycle()

    Column {
        FiltersMock()

        when (state) {
            is ParkingLotsListViewModel.State.Initial,
            is ParkingLotsListViewModel.State.Loading ->
                Text("Loading...")

            is ParkingLotsListViewModel.State.Data ->
                ParkingItemsList(
                    items = (state as ParkingLotsListViewModel.State.Data).parkingLots,
                    onItemClick = { onItemClick() }
                )
        }
    }
}

@Composable
private fun FiltersMock() {
    val filters = listOf("On Street", "Underground", "Multi Storey", "Field", "Garage")
    var selected by remember { mutableIntStateOf(0) }
    ParkingTypeFilterBar(
        filters = filters,
        selectedIndex = selected,
        onSelected = { selected = it }
    )
}

@Composable
private inline fun createViewModel(): ParkingLotsListViewModel =
    viewModel<ParkingLotsListViewModel>(
        factory = KoinPlatform.getKoin().get<ParkingLotsListViewModel.Factory>(),
    )
