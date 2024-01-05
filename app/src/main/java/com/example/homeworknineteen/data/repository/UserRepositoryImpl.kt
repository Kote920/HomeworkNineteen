package com.example.homeworknineteen.data.repository

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.data.service.UserService
import com.example.homeworknineteen.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {
    override suspend fun getUser(url: String): Flow<Resource<UserResponseDto>> {

        return flow {
            try {
                emit(Resource.Loading())

                val response = userService.getUser(url)

                if (response.isSuccessful) {
                    val user = response.body()!!
                    emit(Resource.Success(user))
                } else {
                    emit(Resource.Failed("Request Failed"))

                }

            } catch (e: Exception) {

                emit(Resource.Failed("Request Failed"))

            }

        }


    }
}