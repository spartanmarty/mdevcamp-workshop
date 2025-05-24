package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsListViewModel
import org.koin.mp.KoinPlatform

@Composable
fun ParkingLotsListScreen(
    viewModel: ParkingLotsListViewModel = createViewModel(),
    onItemClick: () -> Unit,
) {
    val selectedFilter = remember { mutableStateOf(ParkingPolicyFilter.NO_FILTER) }

    LaunchedEffect(selectedFilter.value) {
        viewModel.loadParkingLots(selectedFilter.value)
    }

    val state: ParkingLotsListViewModel.State by viewModel.state.collectAsStateWithLifecycle()

    Column {
        ParkingTypeFilterBar(
            filters = ParkingPolicyFilter.entries,
            selectedFilter = selectedFilter.value,
            onSelected = { selectedFilter.value = it }
        )

        when (state) {
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
private inline fun createViewModel(): ParkingLotsListViewModel =
    viewModel<ParkingLotsListViewModel>(
        factory = KoinPlatform.getKoin().get<ParkingLotsListViewModel.Factory>(),
    )
