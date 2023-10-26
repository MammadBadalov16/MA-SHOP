package az.mb.shop.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.signup.SignUpUseCase
import az.mb.shop.presentation.signIn.state.SignInState
import az.mb.shop.presentation.signup.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState




    fun signUp(email: String, password: String) {
        signUpUseCase(email = email, password = password).onEach {

            when (it) {
                is Resource.Success -> {
                    _signUpState.value =
                        (SignUpState(isSuccess = true))
                }

                is Resource.Loading -> {
                    _signUpState.value = (SignUpState(isLoading = true))
                }

                is Resource.Error -> {
                    _signUpState.value = (SignUpState(isError = it.message.toString()))
                }
            }

        }.launchIn(viewModelScope)
    }

}