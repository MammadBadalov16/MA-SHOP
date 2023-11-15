package az.mb.shop.data.local.entity.cart

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity

data class CartEntity(
    @Embedded
    val cartAboutEntity: CartAboutEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
        entity = CartImageEntity::class
    )
    val images: List<CartImageEntity>
)
