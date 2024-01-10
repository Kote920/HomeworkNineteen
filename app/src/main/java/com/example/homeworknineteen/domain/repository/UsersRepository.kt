package com.example.homeworknineteen.domain.repository

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.DeleteResponse
import com.example.homeworknineteen.domain.model.DeleteResult

import com.example.homeworknineteen.domain.model.UsersResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UsersRepository {

suspend fun getList(): Flow<Resource<List<UsersResponse>>>

suspend fun deleteUsers(id: String): Flow<Resource<DeleteResponse>>
}