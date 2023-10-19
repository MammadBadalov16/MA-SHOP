package az.mb.shop.presentation.home.state

import az.mb.shop.domain.model.Category

data class CategoryState(
    val loading: Boolean = false,
    val categories: List<Category>? = null,
    val error: String = ""
)
