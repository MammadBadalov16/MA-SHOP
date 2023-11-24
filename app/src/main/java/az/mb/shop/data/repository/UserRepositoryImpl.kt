package az.mb.shop.data.repository

import az.mb.shop.domain.model.User
import az.mb.shop.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore
) :
    UserRepository {
    override fun getUserInfo(): FirebaseUser? {


        return firebaseAuth.currentUser

    }

    override suspend fun addUserAdditionalInfo(user: User): Boolean {

        val idUser = firebaseAuth.currentUser!!.uid
        var response: Boolean = false

        val documentReference: DocumentReference =
            firebaseFireStore.collection("Users").document(idUser)

        documentReference.set(user).addOnCompleteListener() {
            response = it.isSuccessful
        }.await()

        return response
    }

    override suspend fun getUserAdditionalInfo(): DocumentSnapshot? {
        val idUser = firebaseAuth.currentUser!!.uid
        val documentReference = firebaseFireStore.collection("Users").document(idUser)

        return documentReference.get().await()


    }
}