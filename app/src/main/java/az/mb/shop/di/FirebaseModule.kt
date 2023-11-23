package az.mb.shop.di

import az.mb.shop.data.repository.AuthRepositoryImpl
import az.mb.shop.data.repository.UserRepositoryImpl
import az.mb.shop.domain.repository.AuthRepository
import az.mb.shop.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseStore() = FirebaseFirestore.getInstance();


    @Provides
    @Singleton
    fun providesRepositoryImpl(
        firebaseAuth: FirebaseAuth,
        firebaseFireStore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseFireStore)
    }

    @Provides
    @Singleton
    fun providesUserRepositoryImpl(
        firebaseAuth: FirebaseAuth, firebaseFireStore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(firebaseAuth, firebaseFireStore)
    }

}