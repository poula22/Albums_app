package com.bosta_task.album.presentation.albumDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta_task.album.presentation.profileScreen.ProfileUiState
import com.bosta_task.domain.PhotosList
import com.bosta_task.domain.common.Resource
import com.bosta_task.domain.customExceptions.EmptyListException
import com.bosta_task.domain.useCase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
):ViewModel() {
    private val _albumScreenState= MutableStateFlow<AlbumUiState?>(null)
    val albumUiState: StateFlow<AlbumUiState?>
        get() = _albumScreenState

    private var photosList:PhotosList?=null

    fun fetchPhotos(albumId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase(albumId).collectLatest {
                when(it){
                    is Resource.Success->{
                        photosList=it.data
                        _albumScreenState.emit(AlbumUiState.Success(photosList!!))
                    }
                    is Resource.Loading->{
                        _albumScreenState.emit(AlbumUiState.Loading)
                    }
                    is Resource.Error->{
                        if (it.exception is EmptyListException) _albumScreenState.emit(AlbumUiState.Error(it.exception))
                        else throw it.exception
                    }
                    else -> {}
                }
            }
        }
    }

    fun searchInPhotos(query:String){
        viewModelScope.launch(Dispatchers.Default) {
            _albumScreenState.update {
                if (it is AlbumUiState.Success){
                    if (query.isBlank()){
                        it.copy(photosList = photosList!!)
                    }else{
                        it.copy(photosList = photosList!!.filter { it.title.contains(query) })
                    }
                }else{
                    it
                }
            }
        }
    }
}