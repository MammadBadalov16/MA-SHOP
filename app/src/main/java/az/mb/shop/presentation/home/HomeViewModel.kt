package az.mb.shop.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Constants
import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.use_case.get_category.GetCategoryUseCase
import az.mb.shop.domain.use_case.get_prodocts_of_category.GetProductsOfCategoryUseCase
import az.mb.shop.domain.use_case.product.GetProductUseCase
import az.mb.shop.domain.use_case.product.GetProductsUseCase
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


    init {
        getCategories()
        getProducts()
        //getProductById(80)

    }


    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.FavProduct -> {
                viewModelScope.launch {
                    Log.e("event", event.toString())
                    //productUseCase.addFavProduct.invoke(event)
                    val product = event.data
                    productUseCase.addFavoriteProduct.invoke(
                        FavProductAboutEntity(
                            id = product.id,
                            brand = product.brand,
                            category = product.category,
                            discountPercentage = product.discountPercentage,
                            price = product.price,
                            rating = product.rating,
                            stock = product.stock,
                            thumbnail = product.thumbnail,
                            title = product.title,
                            description = product.description
                        )
                    )
                    val list: MutableList<FavProductImageEntity> = mutableListOf()
                    product.images.forEach {
                        list.add(FavProductImageEntity(productId = product.id, url = it))
                    }
                    productUseCase.addFavProductImages(list)
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
}
