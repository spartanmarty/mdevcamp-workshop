package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.livesport.workshop.parkinglots.ui.theme.Spacings

@Composable
fun ParkingTypeFilterBar(
    filters: List<String>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacings.large),
        horizontalArrangement = Arrangement.spacedBy(Spacings.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { Spacer(modifier = Modifier.width(Spacings.medium)) }
        itemsIndexed(filters) { idx, label ->
            val selected = idx == selectedIndex
            FilterChip(selected, label) { onSelected(idx) }
        }
        item { Spacer(modifier = Modifier.width(Spacings.medium)) }
    }
}

// TODO needs rework, new ripple API works oddly and is displaying only as a circle with given radius
@Composable
private fun FilterChip(selected: Boolean, label: String, onClick: () -> Unit) {
    val shape = RoundedCornerShape(40.dp)
    Box(
        modifier = Modifier
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                shape = shape
            )
            .clickable(enabled = true, onClick = onClick)
            .padding(horizontal = Spacings.xlarge, vertical = Spacings.large)
    ) {
        Text(
            text = label,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}
