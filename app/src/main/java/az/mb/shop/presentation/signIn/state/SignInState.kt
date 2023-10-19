package az.mb.shop.presentation.signIn.state


data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isEmailVerify: Boolean = false,
    val isError: String? = ""

)