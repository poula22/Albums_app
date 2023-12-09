package com.bosta_task.data.remote.dataSource

import com.bosta_task.data.remote.webService.PhotosWebService
import com.bosta_task.data.toPhotoDomainModel
import com.bosta_task.domain.PhotosList
import com.bosta_task.domain.dataSource.PhotosDataSource
import javax.inject.Inject

class PhotosRemoteDataSourceImp @Inject constructor(
    private val photosWebService: PhotosWebService
):PhotosDataSource {
    override suspend fun fetchPhotos(albumId: Int): PhotosList {
        return photosWebService.fetchPhotosByAlbumId(albumId).map { it.toPhotoDomainModel() }
    }
}