package az.mb.shop.presentation.signIn

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import az.mb.shop.presentation.signup.SignUpViewModel

@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel()) {

    Log.e("SignInScreen", viewModel.signInState.value.toString())

}