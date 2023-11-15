package az.mb.shop.data.local.entity.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imagesId: Int? = null,
    val productId: Int,
    val url: String
)
