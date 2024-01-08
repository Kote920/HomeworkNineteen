package com.example.homeworknineteen.domain.useCase

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.UserResponse
import com.example.homeworknineteen.presentation.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserDetailsUseCase {

    suspend fun execute(userId: Int): Flow<Resource<User>>
}