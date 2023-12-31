package az.mb.shop.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.model.User
import az.mb.shop.domain.use_case.auth.SignUpUseCase
import az.mb.shop.domain.use_case.user.AddUserAdditionalInfoUseCase
import az.mb.shop.presentation.signup.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val addUserAdditionalInfoUseCase: AddUserAdditionalInfoUseCase
) : ViewModel() {

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState


    fun signUp(email: String, password: String, name: String, surname: String) {
        signUpUseCase(
            email = email,
            password = password,
            nameAndSurname = "$name $surname"
        ).onEach {

            when (it) {
                is Resource.Success -> {

                    val user = User(
                        uid = it.data?.user?.uid,
                        name = name,
                        surname = surname,
                        email = email
                    )
                    addUserAdditionalInfoUseCase.invoke(user = user)

                    _signUpState.value =
                        (SignUpState(isSuccess = true))
                }

                is Resource.Loading -> {
                    _signUpState.value = (SignUpState(isLoading = true))
                }

                is Resource.Error -> {
                    _signUpState.value = (SignUpState(isError = it.message ?: "Please try again"))
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

}