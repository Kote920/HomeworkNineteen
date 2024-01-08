package com.example.homeworknineteen.data.repository

import com.example.homeworknineteen.data.common.HandleResponse
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.mapper.toDomain
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.data.service.UserService
import com.example.homeworknineteen.domain.model.UserResponse
import com.example.homeworknineteen.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val handleResponse: HandleResponse
) :
    UserRepository {
    override suspend fun getUser(url: String): Flow<Resource<UserResponse>> {


        return handleResponse.safeApiCall {
            userService.getUser(url)
        }.map {
            when(it){
                is Resource.Success -> Resource.Success(it.responseData.toDomain()!!)
                is Resource.Failed -> Resource.Failed(it.message)
                is Resource.Loading -> Resource.Loading()

            }
        }

    }
}