package az.mb.shop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import az.mb.shop.data.local.dao.CartDao
import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.cart.CartAboutEntity
import az.mb.shop.data.local.entity.cart.CartEntity
import az.mb.shop.data.local.entity.cart.CartImageEntity
import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity

@Database(
    entities = [
        FavProductAboutEntity::class,
        FavProductImageEntity::class,
        CartAboutEntity::class,
        CartImageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {

    abstract val favoriteProductDao: FavoriteProductDao

    abstract val cartDao: CartDao


}