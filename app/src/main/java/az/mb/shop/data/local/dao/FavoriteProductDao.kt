package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.mb.shop.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {

    @Query("SELECT * FROM productentity")
    fun getFavoriteProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE productId = :productId")
    suspend fun getFavoriteProductById(productId: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteFavoriteProduct(productEntity: ProductEntity)


}