package az.mb.shop.domain.use_case.user

import az.mb.shop.common.Resource
import az.mb.shop.domain.model.User
import az.mb.shop.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetUserAdditionalUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Resource<User>> = channelFlow {
        try {
            send(Resource.Loading())
            val user = userRepository.getUserAdditionalInfo()?.toObject(User::class.java)
            send(Resource.Success(user))
        } catch (e: Exception) {
            send(Resource.Error(e.message.toString()))
        }
    }
}