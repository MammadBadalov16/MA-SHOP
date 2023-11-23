package az.mb.shop.data.repository

import az.mb.shop.domain.model.User
import az.mb.shop.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore
) :
    UserRepository {
    override fun getUserInfo(): FirebaseUser? {

        

        return firebaseAuth.currentUser

    }

    override suspend fun addUserAdditionalInfo(user: User): Task<Void> {

        val idUser = firebaseAuth.currentUser!!.uid

        val documentReference: DocumentReference =
            firebaseFireStore.collection("Users").document(idUser)

        val request: MutableMap<String, Any> = HashMap()
        request["address"] = user.address.toString()
        request["phone"] = user.phone.toString()


        return documentReference.set(request).addOnCompleteListener {}

    }
}