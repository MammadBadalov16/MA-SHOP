package az.mb.shop.common.check_network

import kotlinx.coroutines.flow.Flow

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
    object Lost : ConnectionState()
    object Losing : ConnectionState()
}