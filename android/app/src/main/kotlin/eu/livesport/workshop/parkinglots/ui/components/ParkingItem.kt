package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.data.ParkingItemModel
import eu.livesport.workshop.parkinglots.data.parkingItemsMock
import eu.livesport.workshop.parkinglots.ui.common.LabelValueText
import eu.livesport.workshop.parkinglots.ui.theme.ParkingLotsTheme

@Composable
fun ParkingItem(
    model: ParkingItemModel,
    modifier: Modifier = Modifier,
    onItemClick: (ParkingItemModel) -> Unit = {},
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
            ParkingName(model.title)
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
fun ParkingInfo(model: ParkingItemModel) {
    LabelValueText(label = stringResource(R.string.label_capacity), value = model.capacity.toString())
    LabelValueText(label = stringResource(R.string.label_address), value = model.address)
    LabelValueText(
        label = stringResource(R.string.label_covered),
        value = if (model.covered) stringResource(R.string.covered_yes) else stringResource(R.string.covered_no)
    )
    LabelValueText(label = stringResource(R.string.label_prohibitions), value = model.prohibitions.joinToString(", "))
}

@Preview
@Composable
fun ParkingItemPreview() {
    ParkingLotsTheme {
        ParkingItem(parkingItemsMock[0])
    }
}