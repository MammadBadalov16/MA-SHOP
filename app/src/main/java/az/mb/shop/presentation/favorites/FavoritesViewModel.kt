package az.mb.shop.presentation.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.use_case.product.GetProductUseCase
import az.mb.shop.domain.use_case.product.ProductUseCase
import az.mb.shop.presentation.home.state.ProductState
import az.mb.shop.presentation.home.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getProductUseCase: ProductUseCase,
) : ViewModel() {

    private val _stateFavProducts = mutableStateOf(ProductsState())
    val stateFavProducts: State<ProductsState> = _stateFavProducts


    init {
        getFavProducts()
    }


     fun getFavProducts() {
        getProductUseCase.getFavoriteProducts.invoke().onEach { it ->

            when (it) {
                is Resource.Loading -> {
                    _stateFavProducts.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {

                    if (it.data != null)
                        it.data.onEach { data ->

                            _stateFavProducts.value =
                                ProductsState(products = data.map { it.toProduct() })


                        }.launchIn(viewModelScope)
                }

                is Resource.Error -> {

                    _stateFavProducts.value =
                        ProductsState(error = it.message ?: Constants.unknownError)
                }
            }
        }.launchIn(viewModelScope)
    }
}



