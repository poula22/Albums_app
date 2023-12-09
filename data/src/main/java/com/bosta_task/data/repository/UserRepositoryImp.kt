package com.bosta_task.data.repository

import com.bosta_task.domain.dataSource.UserDataSource
import com.bosta_task.domain.model.UserDomainModel
import com.bosta_task.domain.repository.UserRepository
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun getRandomUser(): UserDomainModel {
         try {
             return userDataSource.getRandomUser()
         }catch (ex: HttpException){
             //map data layer exception to domain layer custom exceptions if there and throw it
             throw ex
         }catch (ex:Exception){
             throw ex
         }
    }
}