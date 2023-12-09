package com.bosta_task.data

import com.bosta_task.data.remote.model.AlbumsResponseItem
import com.bosta_task.data.remote.model.PhotosResponseItem
import com.bosta_task.data.remote.model.UserResponse
import com.bosta_task.domain.model.AlbumDomainModel
import com.bosta_task.domain.model.PhotoDomainModel
import com.bosta_task.domain.model.UserDomainModel

fun UserResponse.toUserDomainModel() = UserDomainModel(
    id=id!!,
    name=name!!,
    address="${address?.street}, ${address?.suite}, ${address?.city},\n${address?.zipcode}"
)

fun PhotosResponseItem.toPhotoDomainModel()=PhotoDomainModel(
    id = id!!,
    title=title!!,
    url=url!!,
    thumbnailUrl=thumbnailUrl!!
)

fun AlbumsResponseItem.toAlbumDomainModel()=AlbumDomainModel(
    id=id!!,
    title=title!!
)