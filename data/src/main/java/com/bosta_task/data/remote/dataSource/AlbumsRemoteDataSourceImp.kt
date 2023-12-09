package com.bosta_task.data.remote.dataSource

import com.bosta_task.data.remote.webService.AlbumsWebService
import com.bosta_task.data.toAlbumDomainModel
import com.bosta_task.domain.AlbumsList
import com.bosta_task.domain.dataSource.AlbumsDataSource
import javax.inject.Inject

class AlbumsRemoteDataSourceImp @Inject constructor(
    private val albumsWebService:AlbumsWebService
) :AlbumsDataSource{
    override suspend fun fetchAlbums(userId: Int): AlbumsList {
        return albumsWebService.fetchAlbumsByUserId(userId).map { it.toAlbumDomainModel() }
    }
}