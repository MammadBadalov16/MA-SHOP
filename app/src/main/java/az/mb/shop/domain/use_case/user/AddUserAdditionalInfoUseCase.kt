package az.mb.shop.domain.use_case.user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import az.mb.shop.domain.model.User
import az.mb.shop.domain.repository.UserRepository
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class AddUserAdditionalInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(user: User) {

        userRepository.addUserAdditionalInfo(user)


    }
}