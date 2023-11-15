package az.mb.shop.data.local.entity.favProduct

import androidx.room.Embedded
import androidx.room.Relation

data class FavProductEntity(
    @Embedded
    val favProductAboutEntity: FavProductAboutEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
        entity = FavProductImageEntity::class
    )
    val images: List<FavProductImageEntity>
)