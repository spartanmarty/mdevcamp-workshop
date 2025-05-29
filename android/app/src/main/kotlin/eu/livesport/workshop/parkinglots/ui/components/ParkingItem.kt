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
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.ui.common.LabelValueText
import eu.livesport.workshop.parkinglots.ui.theme.ParkingLotsTheme

@Composable
fun ParkingItem(
    model: ParkingLot,
    modifier: Modifier = Modifier,
    onItemClick: (ParkingLot) -> Unit = {},
) {
    Text("ParkingItem")
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
