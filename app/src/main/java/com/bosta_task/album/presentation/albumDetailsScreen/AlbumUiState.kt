package com.bosta_task.album.presentation.albumDetailsScreen

import com.bosta_task.domain.AlbumsList
import com.bosta_task.domain.PhotosList
import com.bosta_task.domain.model.PhotoDomainModel

sealed interface AlbumUiState {
    data class Success(val photosList:PhotosList):AlbumUiState{
        fun filterList(query:String): List<PhotoDomainModel> {
            return photosList.filter { it.title.contains(query) }
        }
    }
    data class Error(val throwable: Throwable):AlbumUiState
    object Loading:AlbumUiState
}