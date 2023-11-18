package az.mb.shop.domain.use_case.auth

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading())
            val result = authRepository.signIn(email = email, password = password)
           // Log.e(authRepository.getUserToken().result.token)
            emit(Resource.Success(data = result))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}