package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import eu.livesport.workshop.parkinglots.ui.common.Error
import eu.livesport.workshop.parkinglots.ui.common.Loading
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsState
import org.koin.mp.KoinPlatform

@Composable
fun ParkingLotsListScreen(
    onItemClick: (id: String) -> Unit,
    viewModel: ParkingLotsViewModel = createViewModel(),
) {
    val selectedFilter = rememberSaveable { mutableStateOf(ParkingPolicyFilter.NO_FILTER) }

    LaunchedEffect(selectedFilter.value) {
        viewModel.onFilterChange(selectedFilter.value)
    }

    val state: ParkingLotsState by viewModel.state.collectAsStateWithLifecycle()

    Column {
        ParkingTypeFilterBar(
            filters = ParkingPolicyFilter.entries,
            selectedFilter = selectedFilter.value,
            onSelected = { selectedFilter.value = it }
        )

        when (state) {
            is ParkingLotsState.Loading -> Loading()

            is ParkingLotsState.Data ->
                ParkingItemsList(
                    items = (state as ParkingLotsState.Data).parkingLots,
                    onItemClick = { onItemClick(it.id) }
                )

            is ParkingLotsState.Error -> Error(state = state as ParkingLotsState.Error)
        }
    }
}

@Composable
private inline fun createViewModel(): ParkingLotsViewModel =
    viewModel<ParkingLotsViewModel>(
        factory = KoinPlatform.getKoin().get<ParkingLotsViewModel.Factory>(),
    )
