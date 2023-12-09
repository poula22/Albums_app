package com.bosta_task.data.remote.webService

import com.bosta_task.data.remote.model.PhotosResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosWebService {
    @GET("photos")
    suspend fun fetchPhotosByAlbumId(@Query("albumId") albumId:Int): List<PhotosResponseItem>
}