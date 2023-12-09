package com.bosta_task.data.remote.dataSource

import com.bosta_task.data.remote.webService.UserWebService
import com.bosta_task.data.toUserDomainModel
import com.bosta_task.domain.dataSource.UserDataSource
import com.bosta_task.domain.model.UserDomainModel
import javax.inject.Inject

class UserRemoteDataSourceImp @Inject constructor(
    private val userWebService: UserWebService
): UserDataSource {
    override suspend fun getRandomUser(): UserDomainModel {
        return userWebService.fetchRandomUser().toUserDomainModel()
    }
}