package com.example.homeworknineteen.domain.useCaseImpl

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.DeleteResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.useCase.DeleteUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeleteUserUseCaseImpl(private val usersRepository: UsersRepository): DeleteUserUseCase {
    override suspend fun execute(id: Int): Flow<Resource<DeleteResponse>> {
        return usersRepository.deleteUsers(id.toString()).map { resource ->
            when (resource) {
                is Resource.Success -> Resource.Success(resource.responseData)
                is Resource.Failed -> Resource.Failed(resource.message)
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

}