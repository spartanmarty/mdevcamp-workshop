package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.data.favoriteItemsMock
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList

@Composable
fun FavoritesScreen(onItemClick: () -> Unit) {
    Column {
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 16.dp),
            text = stringResource(R.string.label_favorites),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
//        ParkingItemsList(
//            items = favoriteItemsMock,
//            onItemClick = { onItemClick() }
//        )
    }
}