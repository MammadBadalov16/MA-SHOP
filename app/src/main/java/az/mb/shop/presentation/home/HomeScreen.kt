package az.mb.shop.presentation.home

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.model.Product
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.components.ErrorScreen
import az.mb.shop.presentation.home.components.CategoryItem
import az.mb.shop.presentation.home.components.ProductsItem
import az.mb.shop.presentation.home.components.SearchBarM3
import az.mb.shop.presentation.home.components.TopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    drawerState: DrawerState,
    navController: NavController,
) {
    val categoriesState = viewModel.stateCategories.value
    val productsState = viewModel.stateProducts.value
    val favProductsState = viewModel.stateFavProducts.value
    val category = categoriesState.categories
    val products = productsState.products
    val favProducts = favProductsState.products

    var onClickTryAgain by remember { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {


        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
        ) {

            HeaderSection(drawerState = drawerState, changeQuery = {
                query = it
                viewModel.getSearchProducts(query = it)
            })

            if (query == "") {
                Spacer(modifier = Modifier.height(30.dp))

                if (category.isNotEmpty())
                    CategorySection(viewModel = viewModel, category = category)
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (products.isNotEmpty())
                ProductSection(
                    favProducts = favProducts,
                    products = products,
                    navController = navController,
                    viewModel = viewModel
                )

        }

        if (productsState.isLoading)
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                color = Color.Black
            )

    }

    if (categoriesState.error.isNotBlank()) {
        ErrorScreen(error = categoriesState.error) {
            onClickTryAgain = true
        }
    }
    if (productsState.error.isNotBlank()) {
        ErrorScreen(error = productsState.error) {
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
fun HeaderSection(
    drawerState: DrawerState,
    changeQuery: (query: String) -> Unit
) {
    TopBar(drawerState = drawerState)

    Spacer(modifier = Modifier.height(10.dp))

    SearchBarM3(queryChange = {
        changeQuery(it)
    })

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
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "See All",
                fontWeight = FontWeight.Black,
                fontSize = 15.sp,
                color = Color.Black,
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
fun ProductSection(
    favProducts: List<Product>,
    products: List<Product>,
    navController: NavController,
    viewModel: HomeViewModel,
) {
    val favProductsId: MutableList<Int> = mutableListOf()

    favProducts.forEach { favProductsId.add(it.id) }

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.Center,
        content = {
            items(products, key = { products -> products.id }) { item ->
                ProductsItem(product = item,
                    isFav = favProductsId.contains(item.id),
                    onClick = { id ->
                        navigateToProductById(
                            productId = id,
                            navController = navController
                        )
                    }, onClickAddFavorite = { product ->
                        viewModel.onEvent(HomeEvents.AddFavProduct(product))

                    }, onClickRemoveFavorite = { product ->

                        viewModel.onEvent(HomeEvents.RemoveFavProduct(product.id))
                    })
            }
        }
    )
}


fun productsByCategories(viewModel: HomeViewModel, category: String) {
    if (category == "All")
        viewModel.getProducts()
    else
        viewModel.getProductsOfCategory(category = category)
}

fun navigateToProductById(navController: NavController, productId: Int) {

    /* DetailsGraph(
         navController = rememberNavController(),
         startDestination = Screen.Product.route + "/" + productId.toString()
     )*/

    navController.navigate(Screen.Product.route + "/" + productId.toString())

}






