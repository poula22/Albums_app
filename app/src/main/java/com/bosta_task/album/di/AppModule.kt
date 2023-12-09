package com.bosta_task.album.di

import com.bosta_task.data.remote.RetrofitManager
import com.bosta_task.data.remote.dataSource.AlbumsRemoteDataSourceImp
import com.bosta_task.data.remote.dataSource.PhotosRemoteDataSourceImp
import com.bosta_task.data.remote.dataSource.UserRemoteDataSourceImp
import com.bosta_task.data.remote.webService.AlbumsWebService
import com.bosta_task.data.remote.webService.PhotosWebService
import com.bosta_task.data.remote.webService.UserWebService
import com.bosta_task.data.repository.AlbumsRepositoryImp
import com.bosta_task.data.repository.PhotosRepositoryImp
import com.bosta_task.data.repository.UserRepositoryImp
import com.bosta_task.domain.apiManager.ApiManager
import com.bosta_task.domain.dataSource.AlbumsDataSource
import com.bosta_task.domain.dataSource.PhotosDataSource
import com.bosta_task.domain.dataSource.UserDataSource
import com.bosta_task.domain.repository.AlbumsRepository
import com.bosta_task.domain.repository.PhotosRepository
import com.bosta_task.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Singleton
        @Provides
        fun albumsWebServiceProvider(apiManager: ApiManager): AlbumsWebService =
            apiManager.buildWebService(AlbumsWebService::class.java)

        @Singleton
        @Provides
        fun photosWebServiceProvider(apiManager: ApiManager): PhotosWebService =
            apiManager.buildWebService(PhotosWebService::class.java)

        @Singleton
        @Provides
        fun userWebServiceProvider(apiManager: ApiManager):UserWebService =
            apiManager.buildWebService(UserWebService::class.java)
    }
    @Singleton
    @Binds
    abstract fun apiManagerProvider(retrofitManager: RetrofitManager): ApiManager

    @Singleton
    @Binds
    abstract fun photosDataSourceProvider(photosDataSource: PhotosRemoteDataSourceImp): PhotosDataSource

    @Singleton
    @Binds
    abstract fun albumsDataSourceProvider(albumsDataSource: AlbumsRemoteDataSourceImp):AlbumsDataSource

    @Singleton
    @Binds
    abstract fun userDataSourceProvider(userDataSource: UserRemoteDataSourceImp):UserDataSource

    @Singleton
    @Binds
    abstract fun photosRepositoryProvider(photosRepository: PhotosRepositoryImp):PhotosRepository

    @Singleton
    @Binds
    abstract fun userRepositoryProvider(userRepository: UserRepositoryImp):UserRepository

    @Singleton
    @Binds
    abstract fun albumRepositoryProvider(albumsRepository: AlbumsRepositoryImp):AlbumsRepository
}