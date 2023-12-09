package com.bosta_task.data.remote.webService

import com.bosta_task.data.remote.model.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebService {
    @GET("users/{id}")
    suspend fun fetchRandomUser(@Path("id") userId:Int=(1..10).random()):UserResponseDto
}