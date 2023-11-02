package az.mb.shop.presentation.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.product.GetProductUseCase
import az.mb.shop.presentation.home.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct


    init {
        savedStateHandle.get<String>("productId")?.let { productId ->
            getProductById(productId.toInt())
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


}
