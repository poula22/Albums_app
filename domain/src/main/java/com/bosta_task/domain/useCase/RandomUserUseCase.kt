package com.bosta_task.domain.useCase

import com.bosta_task.domain.common.toResource
import com.bosta_task.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomUserUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke()= flow {emit(userRepository.getRandomUser())}.toResource()
}