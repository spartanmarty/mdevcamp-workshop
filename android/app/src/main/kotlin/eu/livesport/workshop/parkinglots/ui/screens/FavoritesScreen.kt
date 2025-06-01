package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.ui.common.Error
import eu.livesport.workshop.parkinglots.ui.common.Loading
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.viewmodel.FavoritesViewModel
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsState
import org.koin.mp.KoinPlatform

@Composable
fun FavoritesScreen(
    onItemClick: (id: String) -> Unit,
    viewModel: FavoritesViewModel = createViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavoriteParkingLots()
    }

    val state: ParkingLotsState by viewModel.state.collectAsStateWithLifecycle()
    Column {
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 16.dp),
            text = stringResource(R.string.label_favorites),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        when (state) {
            is ParkingLotsState.Loading -> Loading()

            is ParkingLotsState.Data -> {
                val items = (state as ParkingLotsState.Data).parkingLots
                ParkingItemsList(
                    items = items,
                    onItemClick = { onItemClick(it.id) }
                )
            }

            is ParkingLotsState.Error -> Error(state = state as ParkingLotsState.Error)
        }
    }
}

@Composable
private fun createViewModel(): FavoritesViewModel =
    viewModel<FavoritesViewModel>(
        factory = KoinPlatform.getKoin().get<FavoritesViewModel.Factory>(),
    )
