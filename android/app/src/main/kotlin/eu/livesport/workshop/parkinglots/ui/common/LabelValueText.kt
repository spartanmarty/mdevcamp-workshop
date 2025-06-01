package eu.livesport.workshop.parkinglots.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun LabelValueText(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(label)
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        },
        style = style
    )
}
