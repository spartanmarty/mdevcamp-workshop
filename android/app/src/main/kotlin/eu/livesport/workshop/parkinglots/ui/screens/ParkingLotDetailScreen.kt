package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.data.ParkingDetailModel
import eu.livesport.workshop.parkinglots.ui.common.LabelValueText
import eu.livesport.workshop.parkinglots.ui.theme.Spacings

@Composable
fun ParkingLotDetailScreen(model: ParkingDetailModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacings.large)
            .padding(top = Spacings.xlarge)
    ) {
        // Title
        Text(
            text = model.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = Spacings.xlarge).align(Alignment.CenterHorizontally)
        )

        LabelValueText(
            label = "Address: ",
            value = model.address,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(Spacings.medium))
        LabelValueText(
            label = "Capacity: ",
            value = model.capacity.toString(),
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(Spacings.medium))
        LabelValueText(
            label = "Type: ",
            value = model.type,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(Spacings.medium))
        LabelValueText(
            label = "Parking policy: ",
            value = model.parkingPolicy,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(Spacings.large))
        Text(
            text = "Prohibitions:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = Spacings.small)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacings.large)
        ) {
            repeat(3) {
                ProhibitionIcon()
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
