package az.mb.shop.presentation.signup

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel()) {

    Log.e("SignUp", viewModel.signUpState.value.toString())

}