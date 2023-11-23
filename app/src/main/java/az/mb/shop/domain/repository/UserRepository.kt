package az.mb.shop.domain.repository

import az.mb.shop.data.remote.dto.UserDto
import az.mb.shop.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun getUserInfo(): FirebaseUser?
    suspend fun addUserAdditionalInfo(user: User): Task<Void>

}