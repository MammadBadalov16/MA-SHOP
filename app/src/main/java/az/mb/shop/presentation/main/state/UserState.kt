package az.mb.shop.presentation.main.state

import com.google.firebase.auth.FirebaseUser

data class UserState(
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val isError: String = ""
)
