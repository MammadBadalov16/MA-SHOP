package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.data.local.entity.FavProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT  * FROM CartEntity ")
    fun getCarts(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cartEntity: CartEntity)

    @Delete
    suspend fun deleteCart(cartEntity: CartEntity)


}