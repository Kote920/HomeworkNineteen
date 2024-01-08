package com.example.homeworknineteen.domain.useCaseImpl

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.mapper.toPresentation
import com.example.homeworknineteen.domain.model.UserResponse
import com.example.homeworknineteen.domain.repository.UserRepository
import com.example.homeworknineteen.domain.useCase.GetUserDetailsUseCase
import com.example.homeworknineteen.presentation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserDetailsUseCaseImpl(private val userRepository: UserRepository) :
    GetUserDetailsUseCase {
//    override suspend fun execute(userId: Int): Flow<Resource<User>> {
//
//        return userRepository.getUser(userId.toString()).map {
//
//        }
override suspend fun execute(userId: Int): Flow<Resource<User>> {
    return userRepository.getUser(userId.toString()).map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(resource.responseData.toPresentation()!!)
            is Resource.Failed -> Resource.Failed(resource.message)
            is Resource.Loading -> Resource.Loading()
        }
    }
}
//    }

}