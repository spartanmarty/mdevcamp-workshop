package eu.livesport.workshop.parkinglots.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.viewmodel.State

@Composable
fun Error(
    state: State.Error,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_warning),
            contentDescription = "Alert",
            tint = Color(0xFF132925),
            modifier = Modifier.size(70.dp)
        )

        Spacer(Modifier.height(16.dp))

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
