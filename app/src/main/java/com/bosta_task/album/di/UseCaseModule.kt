package com.bosta_task.album.di

import com.bosta_task.domain.repository.AlbumsRepository
import com.bosta_task.domain.repository.PhotosRepository
import com.bosta_task.domain.repository.UserRepository
import com.bosta_task.domain.useCase.GetAlbumsUseCase
import com.bosta_task.domain.useCase.GetPhotosUseCase
import com.bosta_task.domain.useCase.RandomUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun getPhotosUseCaseProvider(photosRepository: PhotosRepository):GetPhotosUseCase{
        return GetPhotosUseCase(photosRepository)
    }

    @Provides
    fun getAlbumsUseCaseProvider(albumsRepository: AlbumsRepository):GetAlbumsUseCase{
        return GetAlbumsUseCase(albumsRepository)
    }

    @Provides
    fun randomUserUseCaseProvider(userRepository: UserRepository):RandomUserUseCase{
        return RandomUserUseCase(userRepository)
    }
}