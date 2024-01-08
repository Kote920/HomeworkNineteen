package com.example.homeworknineteen.domain.repository

import com.example.homeworknineteen.data.common.Resource

import com.example.homeworknineteen.domain.model.UsersResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

suspend fun getList(): Flow<Resource<List<UsersResponse>>>
}