package com.bosta_task.domain.dataSource

import com.bosta_task.domain.AlbumsList

interface AlbumsDataSource {
    suspend fun fetchAlbums(userId:Int):AlbumsList
}