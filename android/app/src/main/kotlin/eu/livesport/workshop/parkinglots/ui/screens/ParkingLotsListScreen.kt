package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    LaunchedEffect(Unit) {
        viewModel.loadParkingLots()
    }

    val state: State by viewModel.state.collectAsStateWithLifecycle()

    Column {
        FiltersMock()

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
