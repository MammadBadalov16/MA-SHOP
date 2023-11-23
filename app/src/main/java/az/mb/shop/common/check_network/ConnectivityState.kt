package az.mb.shop.common.check_network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current
    val connectivityObserver = NetworkConnectivityObserver(context)

    return connectivityObserver.observe().collectAsState(initial = ConnectionState.Unavailable)
}
