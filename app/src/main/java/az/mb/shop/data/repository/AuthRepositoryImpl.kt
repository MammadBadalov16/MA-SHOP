package az.mb.shop.data.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import az.mb.shop.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResult {

        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUp(email: String, password: String): AuthResult {
        val result =
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) firebaseAuth.currentUser?.sendEmailVerification()
            }.await()
        return result
    }

    override suspend fun googleSignIn(credential: AuthCredential): AuthResult {
        return firebaseAuth.signInWithCredential(credential).await()

    }
}