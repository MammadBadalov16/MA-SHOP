package az.mb.shop.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import az.mb.shop.R
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.ui.theme.placeHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldEmail(
    initOldValue: String,
    onChangeValue: (String) -> Unit
) {
    var emailValue by rememberSaveable { mutableStateOf("") }
    emailValue = initOldValue

    OutlinedTextField(
        value = emailValue,
        onValueChange = {
            emailValue = it
            onChangeValue(it)
        },
        label = { Text("Email") },
        shape = CutCornerShape(percent = 25),
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_username),
                contentDescription = null,
                Modifier.size(24.dp)
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.9f), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldPassword(
    initOldValue: String,
    onChangeValue: (String) -> Unit,
    onChangeVisible: (Boolean) -> Unit
) {
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    passwordValue = initOldValue


    OutlinedTextField(
        value = passwordValue,
        onValueChange = {
            passwordValue = it
            onChangeValue(it)
        },
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_lock),
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisible = !passwordVisible
                onChangeVisible(passwordVisible)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.password_eye),
                    contentDescription = null,
                    tint = if (passwordVisible) Color.DarkGray else placeHolder
                )
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.9f),
        shape = CutCornerShape(percent = 25),
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldString(
    initOldValue: String,
    valueType: String,
    onChangeValue: (String) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    value = initOldValue

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onChangeValue(it)
        },
        label = { Text(valueType) },
        shape = CutCornerShape(percent = 25),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.9f), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun ChangeScreen(questions: String, route: String, navController: NavController, type: Int) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = questions)
        TextButton(onClick = {
            if (type == 0) {
                navController.navigate(Screen.SignUp.route)
            } else {
                navController.popBackStack()
                navController.navigate(Screen.SignIn.route)
            }
        }) {
            Text(text = route, fontSize = 20.sp)
        }
    }

}