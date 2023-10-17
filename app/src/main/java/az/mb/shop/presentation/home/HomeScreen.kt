package az.mb.shop.presentation.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    Log.e("HomeViewModel",viewModel.stateProduct.value.toString())
    Log.e("HomeViewModel",viewModel.stateProducts.value.toString())

}