package az.mb.shop.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.home.components.CategoryItem

import az.mb.shop.presentation.home.components.ProductsItem
import az.mb.shop.presentation.ui.theme.f3

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val categoriesState = viewModel.stateCategories.value
    val productsState = viewModel.stateProducts.value
    val category = categoriesState.categories
    val products = productsState.products
    var onClickTryAgain by remember { mutableStateOf(false) }


    Log.e("HomeViewModel : Categories", viewModel.stateCategories.value.toString())
    Log.e("HomeViewModel : Product", viewModel.stateProduct.value.toString())
    Log.e("HomeViewModel : Products", viewModel.stateProducts.value.toString())
    Log.e("HomeViewModel : ProductsOfCategory", viewModel.stateProductsOfCategory.value.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {

        if (categoriesState.loading)
            CircularProgressIndicator()

        if (category.isNotEmpty())
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
            ) {

                CategorySection(
                    viewModel = viewModel,
                    category = category,
                )

                Spacer(modifier = Modifier.height(30.dp))

                if (productsState.isLoading)
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                if (products.isNotEmpty())
                    ProductSection(products = products)
            }
    }


    if (categoriesState.error.isNotBlank()) {
        errorScreen(error = categoriesState.error) {
            onClickTryAgain = true
        }
    }
    if (productsState.error.isNotBlank()) {
        errorScreen(error = productsState.error) {
            onClickTryAgain = true
        }
    }

    if (onClickTryAgain) {
        onClickTryAgain = false
        viewModel.getCategories()
        viewModel.getProducts()
    }
}

@Composable
fun CategorySection(
    viewModel: HomeViewModel,
    category: MutableList<Category>,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "See All",
                fontWeight = FontWeight.Black,
                fontSize = 15.sp,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        CategoryItem(
            category = category,
            activeCategory = { category ->
                productsByCategories(viewModel = viewModel, category = category)
            },
        )

    }
}

@Composable
fun ProductSection(products: List<Product>) {

    LazyColumn() {
        items(products.windowed(2, 2, true)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                it.forEach { product ->
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        ProductsItem(product = product)
                    }
                }
            }
        }
    }

}


fun productsByCategories(viewModel: HomeViewModel, category: String) {
    if (category == "All")
        viewModel.getProducts()
    else
        viewModel.getProductsOfCategory(category = category)
}

@Composable
fun errorScreen(error: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .background(color = f3, shape = RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = error)
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = { onClick() }) {
                Text(text = "Try Again")
            }
        }
    }
}


