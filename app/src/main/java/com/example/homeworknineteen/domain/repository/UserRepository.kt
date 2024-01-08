package com.example.homeworknineteen.domain.repository

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.domain.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(url: String): Flow<Resource<UserResponse>>
}