package az.mb.shop.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.domain.use_case.user.AddUserAdditionalInfoUseCase
import az.mb.shop.domain.use_case.user.GetUserUseCase
import az.mb.shop.presentation.main.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val additionalInfoUseCase: AddUserAdditionalInfoUseCase,
) : ViewModel() {


    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    private val _addInfoUser = mutableStateOf(false)
    val addInfoUser = _addInfoUser

    private val _connectState = mutableStateOf(true)
    val connectState = _connectState


    init {
        getUserInfo()
    }

    fun onEvents(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.SaveChanges -> {
                checkConnection()
                viewModelScope.launch {
                    additionalInfoUseCase.invoke(user = event.user)
                }
            }
        }

    }

    private fun checkConnection() {


    }

    fun getUserInfo() {
        getUserUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> _userState.value = UserState(isError = it.message.toString())
                is Resource.Loading -> _userState.value = UserState(isBoolean = true)
                is Resource.Success -> {
                    val firebaseUser = it.data
                    _userState.value = UserState(user = firebaseUser)
                }
            }
        }.launchIn(viewModelScope)
    }


}