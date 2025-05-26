package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import eu.livesport.workshop.parkinglots.ui.common.Loading
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel
import eu.livesport.workshop.parkinglots.viewmodel.State

@Composable
fun ParkingLotsListScreen(
    viewModel: ParkingLotsViewModel,
    onItemClick: (id: String) -> Unit,
) {
    val selectedFilter = remember { mutableStateOf(ParkingPolicyFilter.NO_FILTER) }

    LaunchedEffect(selectedFilter.value) {
        viewModel.loadParkingLots(selectedFilter.value)
    }

    val state: State by viewModel.state.collectAsStateWithLifecycle()

    Column {
        ParkingTypeFilterBar(
            filters = ParkingPolicyFilter.entries,
            selectedFilter = selectedFilter.value,
            onSelected = { selectedFilter.value = it }
        )

        when (state) {
            is State.Loading ->
                Loading()

            is State.Data ->
                ParkingItemsList(
                    items = (state as State.Data).parkingLots,
                    onItemClick = { onItemClick(it.id) }
                )
        }
    }
}
