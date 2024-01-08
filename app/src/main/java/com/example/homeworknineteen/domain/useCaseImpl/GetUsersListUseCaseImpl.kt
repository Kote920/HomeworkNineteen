package com.example.homeworknineteen.domain.useCaseImpl

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.mapper.toPresentation
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.useCase.GetUsersListUseCase
import com.example.homeworknineteen.presentation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsersListUseCaseImpl(private val usersRepository: UsersRepository) : GetUsersListUseCase {
//    override suspend fun execute(): Flow<Resource<List<User>>> {
//        return usersRepository.getList().map {
//
//        }
//    }

    override suspend fun execute(): Flow<Resource<List<User>>> {
        return usersRepository.getList().map { resource ->
            when (resource) {
                is Resource.Success -> Resource.Success(resource.responseData.map { it.toPresentation()})
                is Resource.Failed -> Resource.Failed(resource.message)
                is Resource.Loading -> Resource.Loading()
            }
        }
}
}