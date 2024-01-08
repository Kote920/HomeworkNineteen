package com.example.homeworknineteen.data.repository

import com.example.homeworknineteen.data.common.HandleResponse
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.mapper.toDomain
import com.example.homeworknineteen.data.service.UsersService
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersService: UsersService,
    private val handleResponse: HandleResponse

) : UsersRepository {
    override suspend fun getList(): Flow<Resource<List<UsersResponse>>> {
        return handleResponse.safeApiCall {
            usersService.getUsersList()
        }.map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.responseData.map { it.toDomain() })
                is Resource.Failed -> Resource.Failed(res.message)
                is Resource.Loading -> Resource.Loading()
            }
        }


    }


}