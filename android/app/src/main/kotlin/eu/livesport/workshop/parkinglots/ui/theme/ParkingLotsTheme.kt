package eu.livesport.workshop.parkinglots.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val parkingLotsColors = lightColorScheme(
    primary = Color(0xFF92E156),
    onPrimary = Color.Black,
    secondary = Color(0xFF92E156),
    onSecondary = Color.White,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFE8F6DD),
    onSurfaceVariant = Color(0xFF000000),
    secondaryContainer = Color(0xFF92E156),
    onSecondaryContainer = Color.White,
)

@Composable
fun ParkingLotsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = parkingLotsColors,
        content = content
    )
}