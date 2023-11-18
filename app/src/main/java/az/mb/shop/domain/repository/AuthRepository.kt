package az.mb.shop.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GetTokenResult

interface AuthRepository {

    suspend fun signIn(email: String, password: String): AuthResult


    suspend fun signUp(email: String, password: String): AuthResult

    suspend fun googleSignIn(credential: AuthCredential): AuthResult


}