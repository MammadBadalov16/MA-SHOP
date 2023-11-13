package az.mb.shop.presentation.signIn

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import az.mb.shop.R
import az.mb.shop.common.Constants
import az.mb.shop.common.PreferencesManager
import az.mb.shop.presentation.components.ChangeScreen
import az.mb.shop.presentation.components.FieldEmail
import az.mb.shop.presentation.components.FieldPassword
import az.mb.shop.presentation.components.ToastError
import az.mb.shop.presentation.components.ToastErrorC
import az.mb.shop.presentation.components.ToastSuccess
import az.mb.shop.presentation.main.MainScreen


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current.applicationContext
    val sharedPreferences = PreferencesManager(context)
    val state = viewModel.signInState.value
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center,

        ) {

        AnimatedVisibility(visible = !state.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
                    .verticalScroll(rememberScrollState())
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img2),
                    contentDescription = "image",
                    modifier = Modifier.size(300.dp)
                )

                Text(
                    text = "Sign In",
                    color = Black,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    FieldEmail(initOldValue = emailValue, onChangeValue = { emailValue = it })

                    FieldPassword(
                        initOldValue = passwordValue,
                        onChangeValue = {
                            passwordValue = it
                        }, onChangeVisible = {
                            passwordVisible = it
                        })

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(
                        shape = CutCornerShape(percent = 25),
                        onClick = {

                            val message =
                                signIn(
                                    email = emailValue,
                                    password = passwordValue,
                                    viewModel = viewModel
                                )

                            if (message != "") {
                                ToastError(context = context, message = message)
                            }

                            Log.e("HomeViewModel", state.toString())
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        colors = ButtonDefaults.buttonColors(containerColor = Black)
                    ) {
                        Text(
                            text = "Sign In",
                            fontSize = 20.sp,
                            color = White
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ChangeScreen(
                        questions = stringResource(id = R.string.dont_account),
                        route = stringResource(id = R.string.sign_up),
                        navController = navController,
                        type = 0
                    )
                }
            }
        }

        if (state.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(color = Black)
            }
        }
    }

    if (state.isSuccess && !state.isEmailVerify) {
        ToastErrorC(context = context, message = "Please confirm your email address.")
    }

    if (state.isError.isNotBlank()) {
        ToastErrorC(context = context, message = state.isError)
    }

    LaunchedEffect(key1 = state) {
        if (state.isSuccess && state.isEmailVerify) {
            ToastSuccess(context = context, message = "Sign In successfully")
            sharedPreferences.saveData(Constants.TOKEN, "fakeToken")
            isSuccess = true
        }
    }

    if (isSuccess) {
        //  navController.popBackStack()
        MainScreen()
    }
}


fun signIn(email: String, password: String, viewModel: SignInViewModel): String {

    val reqEmail = email.trim()
    val reqPassword = password.trim()

    if (!reqEmail.contains('@'))
        return "Please enter your email address correctly"

    if (reqPassword.length < 6)
        return "Your code must be more than 6 digits"

    viewModel.signIn(reqEmail, reqPassword)

    return ""
}


