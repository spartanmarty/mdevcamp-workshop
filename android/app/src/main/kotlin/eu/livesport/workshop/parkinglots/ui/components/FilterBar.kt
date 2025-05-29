package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

@Composable
fun ParkingTypeFilterBar(
    filters: List<ParkingPolicyFilter>,
    selectedFilter: ParkingPolicyFilter,
    onSelected: (ParkingPolicyFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { Spacer(modifier = Modifier.width(8.dp)) }
        items(filters) { filter ->
            val selected = filter == selectedFilter
            FilterChip(selected, filter) { onSelected(filter) }
        }
        item { Spacer(modifier = Modifier.width(8.dp)) }
    }
}

@Composable
private fun FilterChip(selected: Boolean, filter: ParkingPolicyFilter, onClick: () -> Unit) {
    Text(text = "filter")
}
