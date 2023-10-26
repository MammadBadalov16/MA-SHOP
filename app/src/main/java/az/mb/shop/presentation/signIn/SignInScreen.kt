package az.mb.shop.presentation.signIn

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.mb.shop.R
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.components.ChangeScreen
import az.mb.shop.presentation.components.FieldEmail
import az.mb.shop.presentation.components.FieldPassword


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current.applicationContext
    val state = viewModel.signInState.value
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

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
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }

                            Log.e("HomeViewModel", state.toString())
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    ) {
                        Text(text = "Sign In", fontSize = 20.sp)
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
                CircularProgressIndicator()
            }
        }
    }

    if (state.isSuccess && !state.isEmailVerify) {
        Toast.makeText(
            context, "Please confirm your email address.", Toast.LENGTH_SHORT
        ).show()
    }

    if (state.isError != "") {
        Toast.makeText(context, state.isError, Toast.LENGTH_SHORT).show()
    }
        LaunchedEffect(key1 = state) {
            if (state.isSuccess && state.isEmailVerify) {
                Toast.makeText(context, "Sign In successfully", Toast.LENGTH_SHORT)
                .show()
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }
}


fun signIn(email: String, password: String, viewModel: SignInViewModel): String {

    val email = email.trim()
    val password = password.trim()

    if (!email.contains('@'))
        return "Please enter your email address correctly"

    if (password.length < 6)
        return "Your code must be more than 6 digits"

    viewModel.signIn(email, password)

    return ""
}


