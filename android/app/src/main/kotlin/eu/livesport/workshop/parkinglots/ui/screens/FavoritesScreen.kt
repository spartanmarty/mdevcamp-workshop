package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.ui.common.Loading
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.viewmodel.FavoritesViewModel
import eu.livesport.workshop.parkinglots.viewmodel.State
import org.koin.mp.KoinPlatform

@Composable
fun FavoritesScreen(
    onItemClick: (id: String) -> Unit,
    viewModel: FavoritesViewModel = createViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavoriteParkingLots()
    }

    val state: State by viewModel.state.collectAsStateWithLifecycle()
    Column {
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 16.dp),
            text = stringResource(R.string.label_favorites),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        when (state) {
            is State.Loading ->
                Loading()

            is State.Data -> {
                val items = (state as State.Data).parkingLots
                if (items.isEmpty()) {
                    EmptyFavoritesScreen()
                } else {
                    ParkingItemsList(
                        items = items,
                        onItemClick = { onItemClick(it.id) }
                    )
                }
            }

        }
    }
}

@Composable
private fun EmptyFavoritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_alert), // TODO
                contentDescription = "Alert",
                tint = Color(0xFF132925),
                modifier = Modifier.size(70.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.label_no_favorites),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun createViewModel(): FavoritesViewModel =
    viewModel<FavoritesViewModel>(
        factory = KoinPlatform.getKoin().get<FavoritesViewModel.Factory>(),
    )
