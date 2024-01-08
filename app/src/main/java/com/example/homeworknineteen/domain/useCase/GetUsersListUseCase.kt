package com.example.homeworknineteen.domain.useCase

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.presentation.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersListUseCase {

    suspend fun execute(): Flow<Resource<List<User>>>
}