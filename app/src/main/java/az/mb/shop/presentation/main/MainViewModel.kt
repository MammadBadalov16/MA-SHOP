package az.mb.shop.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.user.GetUserUseCase
import az.mb.shop.presentation.main.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {


    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        getUserUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> _userState.value = UserState(isError = it.message.toString())
                is Resource.Loading -> _userState.value = UserState(isLoading = true)
                is Resource.Success -> {
                    val firebaseUser = it.data
                        _userState.value = UserState(user = firebaseUser)
                }
            }
        }.launchIn(viewModelScope)
    }
}