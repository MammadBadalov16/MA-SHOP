package az.mb.shop.di

import android.app.Application
import androidx.room.Delete
import androidx.room.Room
import az.mb.shop.MainActivity
import az.mb.shop.common.Constants
import az.mb.shop.data.local.ShopDatabase
import az.mb.shop.data.repository.FavoriteProductRepositoryImpl
import az.mb.shop.domain.repository.FavoriteProductRepository
import az.mb.shop.domain.use_case.product.AddFavoriteProductImagesUseCase
import az.mb.shop.domain.use_case.product.AddFavoriteProductUseCase
import az.mb.shop.domain.use_case.product.DeleteFavoriteProductUseCase
import az.mb.shop.domain.use_case.product.GetFavoriteProductUseCase
import az.mb.shop.domain.use_case.product.GetFavoriteProductsUseCase
import az.mb.shop.domain.use_case.product.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideShopDatabase(app: Application): ShopDatabase {
        return Room.databaseBuilder(
            app, ShopDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }



    @Provides
    @Singleton
    fun provideFavoriteProductRepository(db: ShopDatabase): FavoriteProductRepository {
        return FavoriteProductRepositoryImpl(db.favoriteProductDao)
    }



    @Provides
    @Singleton
    fun provideNoteUseCases(repository: FavoriteProductRepository): ProductUseCase {
        return ProductUseCase(
            getFavoriteProduct = GetFavoriteProductUseCase(repository),
            deleteFavoriteProduct = DeleteFavoriteProductUseCase(repository),
            addFavoriteProduct = AddFavoriteProductUseCase(repository),
            getFavoriteProducts = GetFavoriteProductsUseCase(repository),
            addFavProductImages = AddFavoriteProductImagesUseCase(repository)
        )
    }
}