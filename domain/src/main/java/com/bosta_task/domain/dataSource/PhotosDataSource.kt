package com.bosta_task.domain.dataSource

import com.bosta_task.domain.PhotosList

interface PhotosDataSource {
    suspend fun fetchPhotos(albumId:Int):PhotosList
}