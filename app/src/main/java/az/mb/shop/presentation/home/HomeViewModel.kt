package az.mb.shop.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.use_case.get_category.GetCategoryUseCase
import az.mb.shop.domain.use_case.get_prodocts_of_category.GetProductsOfCategoryUseCase
import az.mb.shop.domain.use_case.product.GetProductsUseCase
import az.mb.shop.domain.use_case.product.GetSearchProductsUseCase
import az.mb.shop.domain.use_case.product.ProductUseCase
import az.mb.shop.presentation.home.state.CategoryState
import az.mb.shop.presentation.home.state.ProductState
import az.mb.shop.presentation.home.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getSearchProductsUseCase: GetSearchProductsUseCase,
    private val getProductsOfCategoryUseCase: GetProductsOfCategoryUseCase,
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _stateCategories = mutableStateOf(CategoryState())
    val stateCategories: State<CategoryState> = _stateCategories

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct

    private val _stateProducts = mutableStateOf(ProductsState())
    val stateProducts: State<ProductsState> = _stateProducts

    private val _stateProductsOfCategory = mutableStateOf(ProductsState())
    val stateProductsOfCategory: State<ProductsState> = _stateProductsOfCategory

    private val _stateFavProducts = mutableStateOf(ProductsState())
    val stateFavProducts: State<ProductsState> = _stateFavProducts


    init {
        getCategories()
        getProducts()
        getFavoriteProducts()

    }


    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.AddFavProduct -> {
                viewModelScope.launch {
                    productUseCase.addFavoriteProduct(event.data)
                }
            }

            is HomeEvents.RemoveFavProduct -> {
                viewModelScope.launch {
                    productUseCase.deleteFavoriteProduct(event.id)
                }
            }
        }
    }


    fun getProductsOfCategory(category: String) {
        getProductsOfCategoryUseCase(category = category).onEach {
            when (it) {
                is Resource.Loading -> {
                    _stateProducts.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {
                    _stateProducts.value = ProductsState(products = it.data ?: emptyList())
                }

                is Resource.Error -> {

                    _stateProducts.value =
                        ProductsState(error = it.message ?: Constants.unknownError)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getCategories() {
        getCategoryUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _stateCategories.value =
                        CategoryState(error = it.message ?: Constants.unknownError)
                }

                is Resource.Loading -> {
                    _stateCategories.value = CategoryState(loading = true)
                }

                is Resource.Success -> {
                    val data: MutableList<Category>? = it.data?.toMutableList()
                    data?.add(0, Category("All"))

                    _stateCategories.value =
                        CategoryState(categories = data ?: mutableListOf())

                }

            }
        }.launchIn(viewModelScope)
    }

    fun getProducts() {
        getProductsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _stateProducts.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {
                    _stateProducts.value = ProductsState(products = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateProducts.value =
                        ProductsState(error = it.message ?: Constants.unknownError)

                }

            }
        }.launchIn(viewModelScope)
    }

    fun getSearchProducts(query: String) {
        getSearchProductsUseCase(query = query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _stateProducts.value = ProductsState(isLoading = true)
                }

                is Resource.Success -> {
                    _stateProducts.value = ProductsState(products = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateProducts.value =
                        ProductsState(error = it.message ?: Constants.unknownError)
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getFavoriteProducts() {
        productUseCase.getFavoriteProducts.invoke().onEach { it ->

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

