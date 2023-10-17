package az.mb.shop.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.get_product.GetProductUseCase
import az.mb.shop.domain.use_case.get_products.GetProductsUseCase
import az.mb.shop.presentation.home.state.ProductState
import az.mb.shop.presentation.home.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct

    private val _stateProducts = mutableStateOf(ProductsState())
    val stateProducts: State<ProductsState> = _stateProducts

    init {
        getProducts()
        getProduct(2)
    }


    private fun getProduct(id: Int) {
        getProductUseCase(id).onEach {
            when (it) {
                is Resource.Error -> _stateProduct.value =
                    ProductState(error(it.message.toString()))

                is Resource.Loading -> _stateProduct.value = ProductState(boolean = true)
                is Resource.Success -> _stateProduct.value = ProductState(product = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getProducts() {


        getProductsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _stateProducts.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {
                    _stateProducts.value = ProductsState(products = it.data ?: null)
                }
                is Resource.Error -> {
                    _stateProducts.value = ProductsState(
                        error = error(it.message.toString())
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
