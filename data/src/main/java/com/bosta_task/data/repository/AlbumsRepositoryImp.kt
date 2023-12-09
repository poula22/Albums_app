package com.bosta_task.data.repository

import retrofit2.HttpException
import com.bosta_task.domain.AlbumsList
import com.bosta_task.domain.customExceptions.EmptyListException
import com.bosta_task.domain.dataSource.AlbumsDataSource
import com.bosta_task.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val albumsDataSource: AlbumsDataSource
):AlbumsRepository {
    override suspend fun fetchAlbums(userId: Int): AlbumsList {
        try {
            val albumList=albumsDataSource.fetchAlbums(userId)
            if (albumList.isEmpty()) throw EmptyListException
            return albumList
        }catch (ex: HttpException){
            //map data layer exception to domain layer custom exceptions if there and throw it
            throw ex
        }catch (ex:Exception){
            throw ex
        }
    }
}