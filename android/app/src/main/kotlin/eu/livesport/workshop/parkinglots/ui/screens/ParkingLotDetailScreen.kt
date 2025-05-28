package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingProhibitions
import eu.livesport.workshop.parkinglots.ui.ProhibitionIconResolver
import eu.livesport.workshop.parkinglots.ui.common.Error
import eu.livesport.workshop.parkinglots.ui.common.LabelValueText
import eu.livesport.workshop.parkinglots.ui.common.Loading
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotDetailViewModel
import eu.livesport.workshop.parkinglots.viewmodel.State
import org.koin.mp.KoinPlatform

@Composable
fun ParkingLotDetailScreen(
    parkingLotId: String,
    viewModel: ParkingLotDetailViewModel = createViewModel(),
) {
    LaunchedEffect(parkingLotId) {
        viewModel.loadParkingLotDetail(parkingLotId)
    }

    val state: State by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is State.Loading -> Loading()

        is State.Data ->
            (state as State.Data).parkingLots
                .firstOrNull()
                ?.let { parkingLot ->
                    DetailContent(
                        parkingLot = parkingLot,
                        onFavoriteToggle = { viewModel.toggleFavorite(parkingLot) },
                    )
                }
                ?: Error(State.Error(State.Error.Type.NO_DATA_FOUND))

        is State.Error -> Error(state as State.Error)
    }
}

@Composable
private fun DetailContent(
    parkingLot: ParkingLot,
    onFavoriteToggle: (parkingLot: ParkingLot) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(top = 24.dp)
    ) {
        Text(
            text = parkingLot.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        LabelValueText(
            label = stringResource(id = R.string.label_address),
            value = parkingLot.address ?: stringResource(id = R.string.value_unknown),
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(8.dp))
        LabelValueText(
            label = stringResource(id = R.string.label_capacity),
            value = parkingLot.capacity.toString(),
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.label_prohibitions),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            parkingLot.prohibitions.onEach {
                ProhibitionIcon(it)
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            onClick = { onFavoriteToggle(parkingLot) },
        ) {
            Text(
                text = if (parkingLot.isFavorite) {
                    stringResource(R.string.favorites_remove)
                } else {
                    stringResource(R.string.favorites_add)
                }
            )
        }
    }
}

@Composable
private fun ProhibitionIcon(
    prohibition: ParkingProhibitions,
    prohibitionIconResolver: ProhibitionIconResolver = KoinPlatform.getKoin().get()
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = prohibitionIconResolver.resolveIcon(prohibition)),
            contentDescription = "Prohibition",
            tint = Color(0xFF132925),
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun createViewModel(): ParkingLotDetailViewModel =
    viewModel<ParkingLotDetailViewModel>(
        factory = KoinPlatform.getKoin().get<ParkingLotDetailViewModel.Factory>(),
    )
