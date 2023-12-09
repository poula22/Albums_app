package com.bosta_task.data.remote.webService

import com.bosta_task.data.remote.model.AlbumsResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsWebService {
    @GET("albums")
    suspend fun fetchAlbumsByUserId(@Query("userId") userId:Int):List<AlbumsResponseItem>
}