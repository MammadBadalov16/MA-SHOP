package az.mb.shop.presentation.products

import az.mb.shop.domain.use_case.get_products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsUseCase: GetProductsUseCase) {

}