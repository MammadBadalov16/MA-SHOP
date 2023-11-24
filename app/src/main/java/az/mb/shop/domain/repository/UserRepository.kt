package az.mb.shop.domain.repository

import az.mb.shop.data.remote.dto.UserDto
import az.mb.shop.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import okhttp3.Response

interface UserRepository {
    fun getUserInfo(): FirebaseUser?
    suspend fun addUserAdditionalInfo(user: User): Boolean
    suspend fun getUserAdditionalInfo(): DocumentSnapshot?

}