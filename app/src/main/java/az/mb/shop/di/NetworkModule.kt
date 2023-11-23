package az.mb.shop.di

import android.content.Context
import az.mb.shop.common.Constants
import az.mb.shop.common.check_network.NetworkConnectivityObserver
import az.mb.shop.data.remote.ShopApi
import az.mb.shop.data.repository.RemoteRepositoryImpl
import az.mb.shop.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideShopApi(): ShopApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(shopApi: ShopApi): RemoteRepository {
        return RemoteRepositoryImpl(shopApi)
    }
}