package com.bosta_task.album.presentation

import com.bosta_task.album.presentation.profileScreen.AlbumUiModel
import com.bosta_task.album.presentation.profileScreen.UserUiModel
import com.bosta_task.domain.model.AlbumDomainModel
import com.bosta_task.domain.model.UserDomainModel

fun AlbumDomainModel.toAlbumUiModel() = AlbumUiModel(
    id=id,
    title=title
)

fun UserDomainModel.toUserUiModel()=UserUiModel(
    id=id,
    name=name,
    address=address
)