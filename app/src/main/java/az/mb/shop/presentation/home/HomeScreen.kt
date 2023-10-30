package az.mb.shop.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import az.mb.shop.domain.model.Category
import az.mb.shop.presentation.home.components.CartItem
import az.mb.shop.presentation.home.components.CartItem2
import az.mb.shop.presentation.home.components.Category
import az.mb.shop.presentation.ui.theme.category1
import az.mb.shop.presentation.ui.theme.category2
import az.mb.shop.presentation.ui.theme.category3
import az.mb.shop.presentation.ui.theme.category4

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {


    val listCategory: MutableList<Category> = mutableListOf()
    val listColor = arrayOf(category1, category2, category3, category4)

    val categoriesState = viewModel.stateCategories.value
    val productsState = viewModel.stateProducts.value

    Log.e("HomeViewModel : Categories", viewModel.stateCategories.value.toString())
    Log.e("HomeViewModel : Product", viewModel.stateProduct.value.toString())
    Log.e("HomeViewModel : Products", viewModel.stateProducts.value.toString())
    Log.e("HomeViewModel : ProductsOfCategory", viewModel.stateProductsOfCategory.value.toString())



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.padding(20.dp)) {
            // categoriesState.categories?.let { Category(it) }


            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                verticalItemSpacing = 10.dp,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    productsState.products?.let { it ->
                        items(it.size) {
                            CartItem2(product = productsState.products[it])
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

        }

    }

}


