package az.mb.shop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import az.mb.shop.data.local.dao.CartDao
import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.data.local.entity.FavProductEntity

@Database(
    entities = [
        FavProductEntity::class,
        CartEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {

    abstract val favoriteProductDao: FavoriteProductDao

    abstract val cartDao: CartDao


}