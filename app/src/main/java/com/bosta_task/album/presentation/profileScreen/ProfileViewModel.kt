package com.bosta_task.album.presentation.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta_task.album.presentation.toAlbumUiModel
import com.bosta_task.album.presentation.toUserUiModel
import com.bosta_task.domain.common.Resource
import com.bosta_task.domain.customExceptions.EmptyListException
import com.bosta_task.domain.model.UserDomainModel
import com.bosta_task.domain.useCase.GetAlbumsUseCase
import com.bosta_task.domain.useCase.RandomUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val randomUserUseCase: RandomUserUseCase
) : ViewModel() {
    private val _profileScreenState = MutableStateFlow<ProfileUiState?>(null)
    val profileUiState: StateFlow<ProfileUiState?>
        get() = _profileScreenState

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            randomUserUseCase().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        fetchProfileData(it.data)
                    }

                    is Resource.Error -> _profileScreenState.emit(ProfileUiState.Error(it.exception))
                    is Resource.Loading -> _profileScreenState.emit(ProfileUiState.Loading)
                }
            }
        }
    }

    private fun fetchProfileData(user: UserDomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumsUseCase(user.id).collectLatest { response ->
                when (response) {
                    is Resource.Success -> _profileScreenState.emit(
                        ProfileUiState.Success(
                            user.toUserUiModel(),
                            response.data.map { it.toAlbumUiModel() })
                    )

                    is Resource.Error -> {
                        if (response.exception is EmptyListException) _profileScreenState.emit(
                            ProfileUiState.Error(response.exception)
                        )
                        else throw response.exception
                    }

                    is Resource.Loading -> {
                        //state is loading doesn't need to change
                    }
                }
            }
        }
    }
}