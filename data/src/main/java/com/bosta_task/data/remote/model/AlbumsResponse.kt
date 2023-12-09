package com.bosta_task.data.remote.model

import com.google.gson.annotations.SerializedName

data class AlbumsResponseItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
