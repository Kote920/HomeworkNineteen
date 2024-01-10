package com.example.homeworknineteen.domain.useCase

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.DeleteResponse
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

interface DeleteUserUseCase {

    suspend fun execute(id: Int): Flow<Resource<DeleteResponse>>
}