package az.mb.shop.presentation.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import az.mb.shop.R
import az.mb.shop.presentation.ui.theme.placeHolder


@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel()) {

    LoginPage()

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage() {
    val image = painterResource(id = R.drawable.login_image)
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(White)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Image(painter = image, contentDescription = "image", modifier = Modifier.size(300.dp))


            //  ScrollableColumn(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Sign In",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    label = { Text("Email") },
                )

                OutlinedTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = null,
                                tint = if (passwordVisible) Color.DarkGray else placeHolder
                            )
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f), keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next, // ** Go to next **
                        keyboardType = KeyboardType.Email
                    )
                )


                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Sign In", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Create An Account",
                    modifier = Modifier.clickable(onClick = {
                        /* navController.navigate("register_page") {
                             popUpTo = navController.graph.startDestination
                             launchSingleTop = true
                         }*/
                    })
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}

