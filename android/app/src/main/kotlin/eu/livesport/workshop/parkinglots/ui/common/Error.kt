package eu.livesport.workshop.parkinglots.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.viewmodel.State

@Composable
fun Error(
    state: State.Error,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val errorMessageId: Int = when (state.type) {
            State.Error.Type.NO_DATA_FOUND -> R.string.error_message_no_data
            State.Error.Type.NETWORK -> R.string.error_message_network
            State.Error.Type.UNKNOWN -> R.string.value_unknown
        }

        Text(
            modifier = modifier,
            text = stringResource(id = errorMessageId),
            style = style,
            textAlign = TextAlign.Center,
        )
    }
}
