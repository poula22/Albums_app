package com.bosta_task.domain.useCase

import com.bosta_task.domain.common.toResource
import com.bosta_task.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {
    operator fun invoke(albumId: Int) =
        flow { emit(photosRepository.fetchPhotos(albumId)) }.toResource()
}