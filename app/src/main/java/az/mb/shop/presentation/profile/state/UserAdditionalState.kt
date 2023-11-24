package az.mb.shop.presentation.profile.state

import az.mb.shop.domain.model.User

data class UserAdditionalState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val isError: String = ""
)
