package az.mb.shop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey val productId: Int,
    val quantity: Int
)
