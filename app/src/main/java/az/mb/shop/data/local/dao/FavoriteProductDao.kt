package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {


    @Transaction
    @Query("SELECT  * FROM  FAVPRODUCTABOUTENTITY INNER JOIN FAVPRODUCTIMAGEENTITY ON productId= FAVPRODUCTABOUTENTITY.id GROUP BY FAVPRODUCTABOUTENTITY.id")
    fun getFavoriteProducts(): Flow<List<FavProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(product: FavProductAboutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProductImages(favProductImageEntity: List<FavProductImageEntity>)

    @Delete
    suspend fun deleteFavoriteProduct(product: FavProductAboutEntity)

}