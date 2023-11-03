package az.mb.shop.presentation.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.product.GetProductUseCase
import az.mb.shop.domain.use_case.product.ProductUseCase
import az.mb.shop.presentation.home.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getProductUseCase: ProductUseCase,
) : ViewModel() {

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct


    init {

        getProductUseCase.getFavoriteProducts.invoke().onEach {

            Log.e("FavProducts", it.toString())

        }.launchIn(viewModelScope)

    }


}



