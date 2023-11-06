package az.mb.shop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val productId: Int
)
