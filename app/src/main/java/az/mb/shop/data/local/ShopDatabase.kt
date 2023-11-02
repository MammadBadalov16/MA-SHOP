package az.mb.shop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ShopDatabase : RoomDatabase() {

    abstract val favoriteProductDao: FavoriteProductDao


}