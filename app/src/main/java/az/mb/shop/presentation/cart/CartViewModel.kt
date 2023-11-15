package az.mb.shop.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.data.mapper.toCart
import az.mb.shop.domain.model.Cart
import az.mb.shop.domain.use_case.cart.CartUseCase
import az.mb.shop.domain.use_case.product.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    cartUseCase: CartUseCase,
) : ViewModel() {

    private val _cartState = mutableStateOf(CartState())
    val stateCart: State<CartState> = _cartState

    init {
        cartUseCase.getCartUseCase.invoke().onEach { list ->

            _cartState.value = CartState(cart = list.map { it.toCart() })

        }.launchIn(viewModelScope)
    }
}