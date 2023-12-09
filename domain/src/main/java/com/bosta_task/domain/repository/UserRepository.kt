package com.bosta_task.domain.repository

import com.bosta_task.domain.model.UserDomainModel

interface UserRepository {
    suspend fun getRandomUser():UserDomainModel
}