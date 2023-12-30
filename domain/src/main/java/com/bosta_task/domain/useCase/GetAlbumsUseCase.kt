package com.bosta_task.domain.useCase

import com.bosta_task.domain.common.toResource
import com.bosta_task.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val albumsRepository: AlbumsRepository) {
    operator fun invoke(userId: Int) =
        flow { emit(albumsRepository.fetchAlbums(userId)) }.toResource()
}