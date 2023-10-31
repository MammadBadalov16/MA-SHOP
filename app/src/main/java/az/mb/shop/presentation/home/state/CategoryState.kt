package az.mb.shop.presentation.home.state

import az.mb.shop.domain.model.Category

data class CategoryState(
    val loading: Boolean = false,
    val categories: MutableList<Category> = mutableListOf(),
    val error: String = ""
)
