package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.livesport.workshop.parkinglots.R
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
    androidx.compose.material3.FilterChip(
        modifier = Modifier.height(55.dp),
        selected = true,
        onClick = onClick,
        label = {
            Text(
                text = getChipLabel(filter),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.Black
            )
        },
        shape = RoundedCornerShape(40.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = Color.Black,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = Color.Black
        ),
    )
}

@Composable
private fun getChipLabel(filter: ParkingPolicyFilter): String {
    return when (filter) {
        ParkingPolicyFilter.NO_FILTER -> stringResource(id = R.string.parking_filter_no_filter)
        ParkingPolicyFilter.COMMERCIAL -> stringResource(id = R.string.parking_filter_commercial)
        ParkingPolicyFilter.CUSTOMER_ONLY -> stringResource(id = R.string.parking_filter_customer_only)
        ParkingPolicyFilter.PARK_AND_RIDE -> stringResource(id = R.string.parking_filter_park_and_ride)
        ParkingPolicyFilter.KISS_AND_RIDE -> stringResource(id = R.string.parking_filter_kiss_and_ride)
        ParkingPolicyFilter.PARK_SHARING -> stringResource(id = R.string.parking_filter_park_sharing)
        ParkingPolicyFilter.ZONE -> stringResource(id = R.string.parking_filter_zone)
    }
}
