package com.bosta_task.album.presentation.profileScreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface ProfileUiState{
    data class Success(val userProfile:UserUiModel,val userAlbums:List<AlbumUiModel>):ProfileUiState
    object Loading:ProfileUiState
    data class Error(val throwable: Throwable):ProfileUiState
}

data class AlbumUiModel(val id:Int,val title:String)

data class UserUiModel(val id:Int, val name:String, val address:String)