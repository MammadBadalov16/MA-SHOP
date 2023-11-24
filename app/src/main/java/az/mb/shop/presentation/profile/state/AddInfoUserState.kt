package az.mb.shop.presentation.profile.state

import az.mb.shop.domain.model.User

data class AddInfoUserState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String = ""
)
