package az.mb.shop.data.repository

import android.util.Log
import az.mb.shop.domain.repository.AuthRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore
) :
    AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResult {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()


        val user = firebaseAuth.currentUser
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            it.isEmailVerified

            val uid = it.uid

            Log.e("Userinfo", name.toString())

        }


        return result
    }

    override suspend fun signUp(
        email: String,
        password: String,
        nameAndSurname: String
    ): AuthResult {
        val result =
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful)
                    firebaseAuth.currentUser?.sendEmailVerification()
            }.await()
        val user = firebaseAuth.currentUser
        val profileUpdates = userProfileChangeRequest { displayName = nameAndSurname }
        user!!.updateProfile(profileUpdates).addOnCompleteListener { }
        return result
    }

    override suspend fun googleSignIn(credential: AuthCredential): AuthResult {
        return firebaseAuth.signInWithCredential(credential).await()

    }
}