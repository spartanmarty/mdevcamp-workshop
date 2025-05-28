package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.ui.common.Error
import eu.livesport.workshop.parkinglots.viewmodel.FavoritesViewModel
import eu.livesport.workshop.parkinglots.viewmodel.State

@Composable
fun FavoritesScreen(
    onItemClick: (id: String) -> Unit,
    viewModel: FavoritesViewModel = viewModel<FavoritesViewModel>()
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavoriteParkingLots()
    }

    Column {
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 16.dp),
            text = stringResource(R.string.label_favorites),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Error(state = State.Error(type = State.Error.Type.NO_DATA_FOUND))
    }
}
