package com.bosta_task.domain.dataSource

import com.bosta_task.domain.model.UserDomainModel

interface UserDataSource {
    suspend fun getRandomUser(): UserDomainModel
}