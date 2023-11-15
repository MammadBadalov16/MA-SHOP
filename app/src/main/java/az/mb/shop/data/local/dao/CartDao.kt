package az.mb.shop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import az.mb.shop.data.local.entity.cart.CartAboutEntity
import az.mb.shop.data.local.entity.cart.CartEntity
import az.mb.shop.data.local.entity.cart.CartImageEntity
import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cartAboutEntity: CartAboutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartImages(cartImageEntity: List<CartImageEntity>)

}