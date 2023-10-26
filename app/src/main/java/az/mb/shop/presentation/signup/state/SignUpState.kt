package az.mb.shop.presentation.signup.state


data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String = ""

)