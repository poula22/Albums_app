package com.bosta_task.domain.repository

import com.bosta_task.domain.PhotosList

interface PhotosRepository {
    suspend fun fetchPhotos(albumId:Int):PhotosList
}