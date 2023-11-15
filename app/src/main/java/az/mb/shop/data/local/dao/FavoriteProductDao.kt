package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {


    @Transaction
    @Query("SELECT  * FROM  FAVPRODUCTABOUTENTITY INNER JOIN FAVPRODUCTIMAGEENTITY ON productId= FAVPRODUCTABOUTENTITY.id GROUP BY FAVPRODUCTABOUTENTITY.id")
    fun getFavoriteProducts(): Flow<List<FavProductEntity>>

    @Transaction
    @Query("SELECT  * FROM  FAVPRODUCTABOUTENTITY INNER JOIN FAVPRODUCTIMAGEENTITY ON productId= FAVPRODUCTABOUTENTITY.id WHERE FAVPRODUCTABOUTENTITY.ID = :id GROUP BY FAVPRODUCTABOUTENTITY.id")
    fun getFavoriteProductById(id: Int): Flow<FavProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(product: FavProductAboutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProductImages(favProductImageEntity: List<FavProductImageEntity>)

    @Delete
    suspend fun deleteFavoriteProduct(product: FavProductAboutEntity)

    @Query("DELETE FROM FavProductImageEntity WHERE productId =:id")
    suspend fun deleteFavoriteProductImages(id: Int)

}