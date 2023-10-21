package az.mb.shop.presentation.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.mb.shop.R
import az.mb.shop.common.Screen
import az.mb.shop.presentation.components.FieldEmail
import az.mb.shop.presentation.components.FieldPassword
import az.mb.shop.presentation.components.FieldString

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    var nameValue by rememberSaveable { mutableStateOf("") }
    var surnameValue by rememberSaveable { mutableStateOf("") }
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

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
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                FieldString(valueType = "Name", onChangeValue = { nameValue = it })
                FieldString(valueType = "Surname", onChangeValue = { surnameValue = it })

                FieldEmail(onChangeValue = { emailValue = it })

                FieldPassword(
                    onChangeValue = {
                        passwordValue = it
                    }, onChangeVisible = {
                        passwordVisible = it
                    })

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    shape = CutCornerShape(percent = 25),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Text(text = "Sign Up", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(10.dp))


                Text(
                    text = "", modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screen.SignIn.route)
                    })
                )
            }
        }
    }


}


