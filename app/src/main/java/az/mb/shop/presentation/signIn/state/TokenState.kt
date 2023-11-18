package az.mb.shop.presentation.signIn.state


data class TokenState(
    val isLoading: Boolean = false,
    val token: String? = null,
    val isError: String? = null
)