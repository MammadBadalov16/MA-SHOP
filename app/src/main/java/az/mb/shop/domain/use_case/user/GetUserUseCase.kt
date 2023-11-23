package az.mb.shop.domain.use_case.user

import az.mb.shop.common.Resource
import az.mb.shop.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Resource<FirebaseUser>> = channelFlow {
        try {
            send(Resource.Loading())
            val user = userRepository.getUserInfo()
            send(Resource.Success(user))
        } catch (e: Exception) {
            send(Resource.Error(e.message.toString()))
        }
    }
}