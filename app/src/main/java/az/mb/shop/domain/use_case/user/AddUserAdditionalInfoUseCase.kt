package az.mb.shop.domain.use_case.user

import az.mb.shop.common.Resource
import az.mb.shop.domain.model.User
import az.mb.shop.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddUserAdditionalInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(user: User): Flow<Resource<Boolean>> = channelFlow {
        try {
            send(Resource.Loading())
            val response = userRepository.addUserAdditionalInfo(user)
            if (response)
                send(Resource.Success(true))
            else
                send(Resource.Error("Unknown Error"))
        } catch (e: Exception) {
            send(Resource.Error(e.message.toString()))
        }
    }
}