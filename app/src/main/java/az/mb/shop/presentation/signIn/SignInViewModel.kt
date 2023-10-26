package az.mb.shop.presentation.signIn


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.signin.SignInUseCase
import az.mb.shop.presentation.signIn.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInState = mutableStateOf(SignInState())
    val signInState: State<SignInState> = _signInState

    init {
        //signUp("memmedbedelov32@gmail.com", "memmed0102")
    }

    fun signIn(email: String, password: String) {
        signInUseCase(email = email, password = password).onEach {
            when (it) {
                is Resource.Success -> {
                    if (it.data?.user?.isEmailVerified == true) {
                        _signInState.value = (SignInState(isSuccess = true, isEmailVerify = true))
                    } else {
                        _signInState.value = (SignInState(isEmailVerify = false, isSuccess = true))
                    }
                }

                is Resource.Loading -> {
                    _signInState.value = (SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signInState.value = (SignInState(isError = it.message ?: "Please try again"))
                }
            }

        }.launchIn(viewModelScope)
    }
}