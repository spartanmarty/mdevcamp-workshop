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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.ui.common.LabelValueText
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel

@Composable
fun ParkingLotDetailScreen(
    parkingLotId: String,
    viewModel: ParkingLotsViewModel,
) {

    val model = viewModel.getParkingLot(parkingLotId).collectAsState(null).value

    model?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .padding(top = 24.dp)
        ) {
            // Title
            Text(
                text = model.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp).align(Alignment.CenterHorizontally)
            )

            LabelValueText(
                label = "Address: ",
                value = model.address ?: "Unknown",
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(Modifier.height(8.dp))
            LabelValueText(
                label = "Capacity: ",
                value = model.capacity.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )

//        Spacer(Modifier.height(8.dp))
//        LabelValueText(
//            label = "Type: ",
//            value = model.,
//            style = MaterialTheme.typography.bodyLarge,
//        )
//
//        Spacer(Modifier.height(8.dp))
//        LabelValueText(
//            label = "Parking policy: ",
//            value = model.parkingPolicy,
//            style = MaterialTheme.typography.bodyLarge,
//        )

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Prohibitions:",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(3) {
                    ProhibitionIcon()
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                onClick = { viewModel.toggleFavorite(it) },
            ) {
                Text(
                    text = if (model.isFavorite) {
                        stringResource(R.string.favorites_remove)
                    } else {
                        stringResource(R.string.favorites_add)
                    }
                )

            }
        }
    }
}

@Composable
fun ProhibitionIcon() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_dialog_alert), // TODO
            contentDescription = "Prohibition",
            tint = Color(0xFF132925),
            modifier = Modifier.size(32.dp)
        )
    }
}
