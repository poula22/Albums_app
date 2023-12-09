package com.bosta_task.domain.repository

import com.bosta_task.domain.AlbumsList

interface AlbumsRepository {
    suspend fun fetchAlbums(userId:Int):AlbumsList
}