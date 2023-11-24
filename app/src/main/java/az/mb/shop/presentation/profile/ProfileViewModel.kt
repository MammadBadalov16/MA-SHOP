package az.mb.shop.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mb.shop.common.Resource
import az.mb.shop.common.check_network.ConnectionState
import az.mb.shop.common.check_network.NetworkConnectivityObserver
import az.mb.shop.domain.use_case.user.AddUserAdditionalInfoUseCase
import az.mb.shop.domain.use_case.user.GetUserAdditionalUseCase
import az.mb.shop.domain.use_case.user.GetUserUseCase
import az.mb.shop.presentation.main.state.UserState
import az.mb.shop.presentation.profile.state.AddInfoUserState
import az.mb.shop.presentation.profile.state.UserAdditionalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val additionalInfoUseCase: AddUserAdditionalInfoUseCase,
    private val getUserAdditionalUseCase: GetUserAdditionalUseCase
) : ViewModel() {


    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    private val _addInfoUserState = mutableStateOf(AddInfoUserState())
    val addInfoUserState = _addInfoUserState

    private val _userAdditionalState = mutableStateOf(UserAdditionalState())
    val userAdditionalState: State<UserAdditionalState> = _userAdditionalState


    init {
        getUserInfo()
        getUserAdditionalInfo()
    }

    fun onEvents(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.SaveChanges -> {
                additionalInfoUseCase.invoke(user = event.user).onEach {
                    when (it) {
                        is Resource.Error -> _addInfoUserState.value =
                            AddInfoUserState(isError = it.message.toString())

                        is Resource.Loading -> _addInfoUserState.value =
                            AddInfoUserState(isLoading = true)

                        is Resource.Success -> _addInfoUserState.value =
                            AddInfoUserState(isSuccess = true)
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun getUserInfo() {
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

    private fun getUserAdditionalInfo() {
        getUserAdditionalUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> _userAdditionalState.value =
                    UserAdditionalState(isError = it.message.toString())

                is Resource.Loading -> _userAdditionalState.value =
                    UserAdditionalState(isLoading = true)

                is Resource.Success -> {
                    _userAdditionalState.value = UserAdditionalState(user = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}