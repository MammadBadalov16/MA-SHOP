package az.mb.shop.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import az.mb.shop.domain.model.Cart
import az.mb.shop.domain.use_case.cart.CartUseCase
import az.mb.shop.domain.use_case.product.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    cartUseCase: CartUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _cartState = mutableStateOf(CartState())
    val stateCart: State<CartState> = _cartState

    var list: MutableList<Cart> = mutableListOf()

    init {
      /*  cartUseCase.getCartUseCase.invoke().onEach {

            it.forEach { cartEntity ->

                getProductUseCase(cartEntity.productId).onEach { response ->
                    when (response) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                        is Resource.Success ->
                            if (response.data != null) {
                                list.add(
                                    Cart(
                                        product = response.data,
                                        quantity = cartEntity.quantity
                                    )
                                )
                                _cartState.value = CartState(cart = list)

                            }
                    }
                }.launchIn(viewModelScope)

            }
        }.launchIn(viewModelScope)
*/



    }
}