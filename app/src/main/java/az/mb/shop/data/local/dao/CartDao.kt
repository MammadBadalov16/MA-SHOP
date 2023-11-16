package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.data.local.entity.FavProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT  * FROM CartEntity ORDER BY cartId DESC")
    fun getCarts(): Flow<List<CartEntity>>

    @Insert()
    suspend fun addCart(cartEntity: CartEntity)

    @Query("DELETE FROM CartEntity WHERE id = :id")
    suspend fun deleteCart(id: Int)

}