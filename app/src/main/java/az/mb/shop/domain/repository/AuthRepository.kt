package az.mb.shop.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signIn(email: String, password: String): AuthResult

    suspend fun signUp(email: String, password: String): AuthResult

    suspend fun googleSignIn(credential: AuthCredential): AuthResult


}