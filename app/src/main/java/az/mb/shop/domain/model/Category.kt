package az.mb.shop.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import az.mb.shop.R
import az.mb.shop.presentation.ui.theme.category1

data class Category(
    @DrawableRes
    val iconId: Int? = R.drawable.ic_cate,
    val category: String,
    val color: Color? = category1
)