package az.mb.shop.presentation.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {

    Log.e("HomeViewModel : Categories", viewModel.stateCategories.value.toString())
    Log.e("HomeViewModel : Product", viewModel.stateProduct.value.toString())
    Log.e("HomeViewModel : Products", viewModel.stateProducts.value.toString())
    Log.e("HomeViewModel : ProductsOfCategory", viewModel.stateProductsOfCategory.value.toString())

}