package az.mb.shop.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class FavProductEntity(
    @PrimaryKey(autoGenerate = true)
    val favProductId: Int? = null,
    val id: Int,
    val brand: String?,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val price: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)