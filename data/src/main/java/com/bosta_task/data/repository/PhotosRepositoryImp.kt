package com.bosta_task.data.repository

import com.bosta_task.domain.PhotosList
import com.bosta_task.domain.customExceptions.EmptyListException
import com.bosta_task.domain.dataSource.PhotosDataSource
import com.bosta_task.domain.repository.PhotosRepository
import retrofit2.HttpException
import javax.inject.Inject

class PhotosRepositoryImp @Inject constructor(
    private val photosDataSource: PhotosDataSource
): PhotosRepository {
    override suspend fun fetchPhotos(albumId: Int): PhotosList {
        try {
            val photosList=photosDataSource.fetchPhotos(albumId)
            if (photosList.isEmpty()) throw EmptyListException
            return photosList
        }catch (ex: HttpException){
            //map data layer exception to domain layer custom exceptions if there and throw it
            throw ex
        }catch (ex:Exception){
            throw ex
        }
    }
}