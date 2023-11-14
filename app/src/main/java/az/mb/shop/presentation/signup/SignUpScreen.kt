package az.mb.shop.presentation.signup

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import az.mb.shop.domain.model.User
import az.mb.shop.presentation.components.ChangeScreen
import az.mb.shop.presentation.components.FieldEmail
import az.mb.shop.presentation.components.FieldPassword
import az.mb.shop.presentation.components.FieldString
import az.mb.shop.presentation.components.ToastError
import az.mb.shop.presentation.components.ToastSuccess

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current.applicationContext
    val state = viewModel.signUpState.value
    Log.e("SignUpState", state.toString())

    var nameValue by rememberSaveable { mutableStateOf("") }
    var surnameValue by rememberSaveable { mutableStateOf("") }
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(visible = !state.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())

            ) {

                Image(
                    painter = painterResource(id = R.drawable.img2),
                    contentDescription = "image",
                    modifier = Modifier.size(300.dp)
                )

                Text(
                    text = "Sign Up",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 30.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    FieldString(
                        initOldValue = nameValue,
                        valueType = "Name",
                        onChangeValue = { nameValue = it })
                    FieldString(
                        initOldValue = surnameValue,
                        valueType = "Surname",
                        onChangeValue = { surnameValue = it })

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
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        onClick = {
                            val checkFieldsResponse = checkFields(
                                user = User(
                                    name = nameValue,
                                    surname = surnameValue,
                                    email = emailValue,
                                    password = passwordValue
                                ), viewModel = viewModel
                            )
                            if (checkFieldsResponse != "") {
                                ToastError(context = context, message = checkFieldsResponse)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    ) {
                        Text(text = "Sign Up", fontSize = 20.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ChangeScreen(
                        questions = stringResource(id = R.string.have_account),
                        route = stringResource(id = R.string.sign_in),
                        navController = navController,
                        type = 1
                    )
                }
            }
        }

        if (state.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(color = Color.Black)
            }
        }
    }


    if (state.isSuccess) {
        ToastSuccess(context = context, message = "Account successfully created")
        nameValue = ""
        surnameValue = ""
        emailValue = ""
        passwordValue = ""
    }

    if (state.isError.isNotBlank()) {
        ToastError(context = context, message = state.isError)
    }
}

fun checkFields(user: User, viewModel: SignUpViewModel): String {
    val name = user.name.trim()
    val surname = user.surname.trim()
    val email = user.email.trim()
    val password = user.password.trim()

    if (name.isEmpty() || name == "")
        return "Please enter name."

    if (surname.isEmpty() || surname == "")
        return "Please enter surname"

    if (!email.contains('@'))
        return "Please enter your email address correctly"

    if (password.length < 6)
        return "Your code must be more than 6 digits"

    viewModel.signUp(email, password)

    return ""
}


