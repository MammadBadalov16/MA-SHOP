package az.mb.shop.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.get_category.GetCategoryUseCase
import az.mb.shop.domain.use_case.get_prodocts_of_category.GetProductsOfCategoryUseCase
import az.mb.shop.domain.use_case.get_product.GetProductUseCase
import az.mb.shop.domain.use_case.get_products.GetProductsUseCase
import az.mb.shop.presentation.home.state.CategoryState
import az.mb.shop.presentation.home.state.ProductState
import az.mb.shop.presentation.home.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductsOfCategoryUseCase: GetProductsOfCategoryUseCase
) : ViewModel() {

    private val _stateCategories = mutableStateOf(CategoryState())
    val stateCategories: State<CategoryState> = _stateCategories

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct

    private val _stateProducts = mutableStateOf(ProductsState())
    val stateProducts: State<ProductsState> = _stateProducts

    private val _stateProductsOfCategory = mutableStateOf(ProductsState())
    val stateProductsOfCategory: State<ProductsState> = _stateProductsOfCategory


    init {
        /* getProductsOfCategory("smartphones")
         getCategories()
         getProducts()
         getProductById(2)*/
        getCategories()
        getProducts()

    }

    private fun getProductsOfCategory(category: String) {
        getProductsOfCategoryUseCase(category = category).onEach {
            when (it) {
                is Resource.Loading -> {
                    _stateProductsOfCategory.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {
                    _stateProductsOfCategory.value = ProductsState(products = it.data ?: null)
                }

                is Resource.Error -> {
                    _stateProductsOfCategory.value = ProductsState(
                        error = error(it.message.toString())
                    )
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        getCategoryUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _stateCategories.value = CategoryState(error = it.message.toString())
                }

                is Resource.Loading -> {
                    _stateCategories.value = CategoryState(loading = true)
                }

                is Resource.Success -> {
                    _stateCategories.value = CategoryState(categories = it.data)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getProductById(id: Int) {
        getProductUseCase(id).onEach {
            when (it) {
                is Resource.Error -> _stateProduct.value =
                    ProductState(error(it.message.toString()))

                is Resource.Loading -> _stateProduct.value = ProductState(boolean = true)
                is Resource.Success -> _stateProduct.value = ProductState(product = it.data)
                else -> {}
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

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
