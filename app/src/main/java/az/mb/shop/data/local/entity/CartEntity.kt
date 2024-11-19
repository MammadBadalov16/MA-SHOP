package az.mb.shop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val cartId: Int? = null,
    val id: Int,
    val title: String,
    val price: Double,
    val stock: Int,
    val rating: Double,
    val thumbnail: String,
    var quantity: Int? = null
)
