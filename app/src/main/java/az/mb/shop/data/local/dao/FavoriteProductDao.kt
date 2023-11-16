package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.mb.shop.data.local.entity.FavProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {

    @Query("SELECT  * FROM FavProductEntity ORDER BY favProductId DESC")
    fun getFavoriteProducts(): Flow<List<FavProductEntity>>

    @Query("SELECT  * FROM FavProductEntity WHERE id = :id")
    fun getFavoriteProduct(id: Int): Flow<FavProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(favProductEntity: FavProductEntity)

    @Query("DELETE FROM FavProductEntity WHERE id = :id")
    suspend fun deleteFavoriteProduct(id: Int)

}