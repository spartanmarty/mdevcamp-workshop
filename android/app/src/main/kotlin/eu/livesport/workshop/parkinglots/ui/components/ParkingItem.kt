package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.ui.theme.ParkingLotsTheme

@Composable
fun ParkingItem(
    model: ParkingLot,
    modifier: Modifier = Modifier,
    onItemClick: (ParkingLot) -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { onItemClick(model) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            ParkingName(model.name)
            ParkingInfo(model)
        }

    }
}

@Composable
fun ParkingName(parkingName: String) {
    Text(
        text = parkingName,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
fun ParkingInfo(model: ParkingLot) {
    LabelValueText(
        label = stringResource(R.string.label_capacity),
        value = model.capacity.toString(),
    )
    LabelValueText(
        label = stringResource(R.string.label_address),
        value = model.address.orEmpty().ifBlank { stringResource(R.string.value_unknown) },
    )
    LabelValueText(
        label = stringResource(R.string.label_covered),
        value = when (model.covered) {
            true -> stringResource(R.string.covered_yes)
            false -> stringResource(R.string.covered_no)
            null -> stringResource(R.string.value_unknown)
        },
    )
}

@Preview
@Composable
fun ParkingItemPreview() {
    ParkingLotsTheme {
        ParkingItem(
            ParkingLot(
                id = "park_123",
                name = "Parking 1",
                address = "123 Main St",
                capacity = 100,
                covered = true,
                prohibitions = emptyList(),
            )
        )
    }
}
