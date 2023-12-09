package com.bosta_task.data

import com.bosta_task.data.remote.model.AlbumsResponseItemDto
import com.bosta_task.data.remote.model.PhotosResponseItemDto
import com.bosta_task.data.remote.model.UserResponseDto
import com.bosta_task.domain.model.AlbumDomainModel
import com.bosta_task.domain.model.PhotoDomainModel
import com.bosta_task.domain.model.UserDomainModel

fun UserResponseDto.toUserDomainModel() = UserDomainModel(
    id=id!!,
    name=name!!,
    address="${address?.street}, ${address?.suite}, ${address?.city},\n${address?.zipcode}"
)

fun PhotosResponseItemDto.toPhotoDomainModel()=PhotoDomainModel(
    id = id!!,
    title=title!!,
    url=url!!,
    thumbnailUrl=thumbnailUrl!!
)

fun AlbumsResponseItemDto.toAlbumDomainModel()=AlbumDomainModel(
    id=id!!,
    title=title!!
)