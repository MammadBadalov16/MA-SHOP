package az.mb.shop.presentation.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.use_case.cart.CartUseCase
import az.mb.shop.domain.use_case.product.GetProductUseCase
import az.mb.shop.domain.use_case.product.ProductUseCase
import az.mb.shop.presentation.home.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val productUseCase: ProductUseCase,
    private val cartUseCase: CartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct

    private val _stateFavProducts = mutableStateOf(ProductState())
    val stateFavProducts: State<ProductState> = _stateFavProducts


    init {
        savedStateHandle.get<String>("productId")?.let { productId ->
            getProductById(productId.toInt())
            getFavoriteProductById(productId.toInt())
        }
    }

    fun onEvent(event: ProductScreenEvents) {
        when (event) {
            is ProductScreenEvents.AddFavProduct -> {
                viewModelScope.launch {
                    productUseCase.addFavoriteProduct(event.data)

                }
            }

            is ProductScreenEvents.RemoveFavProduct -> {
                viewModelScope.launch {
                    productUseCase.deleteFavoriteProduct(event.id)
                }
            }

            is ProductScreenEvents.AddToCart -> {
                viewModelScope.launch {
                    cartUseCase.addCartUseCase(
                        product = event.data,
                        quantity = event.quantity
                    )
                }
            }
        }
    }


    private fun getProductById(id: Int) {
        getProductUseCase(id).onEach {
            when (it) {
                is Resource.Error -> _stateProduct.value =
                    ProductState(error = it.message ?: Constants.unknownError)

                is Resource.Loading -> _stateProduct.value = ProductState(isLoading = true)
                is Resource.Success -> _stateProduct.value = ProductState(product = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getFavoriteProductById(id: Int) {
        productUseCase.getFavoriteProduct.invoke(id).onEach {
            try {
                _stateFavProducts.value = ProductState(isLoading = true)
                val products = it.toProduct()
                _stateFavProducts.value = ProductState(product = products)

            } catch (exception: Exception) {
                _stateFavProducts.value = ProductState(error = exception.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}
