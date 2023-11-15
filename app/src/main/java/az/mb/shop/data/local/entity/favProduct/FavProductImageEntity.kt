package az.mb.shop.data.local.entity.favProduct

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavProductImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imagesId: Int? = null,
    val productId: Int,
    val url: String
)

