package com.example.homeworknineteen.domain.repository

import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.domain.model.UserListResponse
import kotlinx.coroutines.flow.Flow

interface UserListRepository {

suspend fun getList(): Flow<Resource<UserListResponse>>
}