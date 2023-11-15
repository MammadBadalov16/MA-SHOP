package az.mb.shop.data.local.entity.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartAboutEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)
