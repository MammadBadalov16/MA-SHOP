package az.mb.shop.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import az.mb.shop.R
import az.mb.shop.common.check_network.ConnectionState
import az.mb.shop.common.check_network.connectivityState
import az.mb.shop.domain.model.User
import az.mb.shop.presentation.components.ErrorScreen
import az.mb.shop.presentation.profile.components.UserInfoTextField
import az.mb.shop.presentation.ui.theme.f5
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    val stateUser = viewModel.userState.value
    val user = stateUser.user

    val stateUserAdditional = viewModel.userAdditionalState.value
    val userAdditional = stateUserAdditional.user

    val stateAddUser = viewModel.addInfoUserState.value

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    var showInternetConnection by rememberSaveable { mutableStateOf(false) }


    var addressValue by rememberSaveable { mutableStateOf("") }
    var phoneValue by rememberSaveable { mutableStateOf("") }

    var onClickTryAgain by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = userAdditional) {
        if (userAdditional?.address != null)
            addressValue = userAdditional.address
        if (userAdditional?.phone != null)
            phoneValue = userAdditional.phone
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {

        if (stateUser.isLoading || stateAddUser.isLoading || stateUserAdditional.isLoading) {
            CircularProgressIndicator(color = Color.Black)
        } else {
            if (user != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Spacer(modifier = Modifier.height(30.dp))

                    SectionHeader(user = user)

                    Spacer(modifier = Modifier.height(20.dp))

                    SectionChangeUserInfo(
                        firebaseUser = user,
                        initOldAddressValue = addressValue,
                        initOldPhoneValue = phoneValue,
                        onChangeAddressValue = { addressValue = it },
                        onChangePhoneValue = { phoneValue = it },
                        onSaveChanges = {
                            if (!isConnected) {
                                showInternetConnection = true
                            } else {
                                viewModel.onEvents(
                                    event = ProfileEvents.SaveChanges(
                                        user = User(
                                            name = userAdditional?.name,
                                            surname = userAdditional?.surname,
                                            email = userAdditional?.email,
                                            address = addressValue,
                                            phone = phoneValue
                                        )
                                    )
                                )
                            }
                        })
                }
            }
        }

        if (showInternetConnection)
            ErrorScreen(
                error = "Please check internet connection and try again",
                onClick = { onClickTryAgain = true })

        if (stateUser.isError.isNotBlank())
            ErrorScreen(error = stateUser.isError, onClick = { onClickTryAgain = true })

        if (onClickTryAgain) {
            viewModel.getUserInfo()
        }
    }
}


@Composable
fun SectionHeader(user: FirebaseUser) {
    Image(
        painter = painterResource(id = R.drawable.drawer_user),
        contentDescription = "user_image",
        Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(f5)
    )
    Spacer(modifier = Modifier.height(5.dp))

    if ((user.displayName != null) && (user.email != null)) {
        Text(
            text = user.displayName!!,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = user.email!!, fontSize = 14.sp, color = Color.Black)
    }
}

@Composable
fun SectionChangeUserInfo(
    firebaseUser: FirebaseUser,
    initOldAddressValue: String,
    initOldPhoneValue: String,
    onChangeAddressValue: (value: String) -> Unit,
    onChangePhoneValue: (value: String) -> Unit,
    onSaveChanges: () -> Unit
) {
    /*  var addressValue by rememberSaveable { mutableStateOf("") }
      var phoneValue by rememberSaveable { mutableStateOf("") }*/

    UserInfoTextField(
        label = "Name",
        initOldValue = firebaseUser.displayName!!.split(" ")[0],
        onChangeValue = {}
    )

    Spacer(modifier = Modifier.height(10.dp))

    UserInfoTextField(
        label = "Surname",
        initOldValue = firebaseUser.displayName!!.split(" ")[1],
        onChangeValue = {}
    )

    Spacer(modifier = Modifier.height(10.dp))

    UserInfoTextField(
        label = "Email",
        initOldValue = firebaseUser.email!!,
        onChangeValue = {}
    )

    Spacer(modifier = Modifier.height(10.dp))

    UserInfoTextField(
        label = "Address",
        initOldValue = initOldAddressValue,
        isReadOnly = false,
        onChangeValue = { onChangeAddressValue(it) }
    )

    Spacer(modifier = Modifier.height(10.dp))

    UserInfoTextField(
        label = "Phone",
        initOldValue = initOldPhoneValue,
        isReadOnly = false,
        onChangeValue = { onChangePhoneValue(it) }
    )

    Spacer(modifier = Modifier.height(20.dp))


    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { onSaveChanges() },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Black
        )
    ) {
        Text(text = "Save")
    }

}