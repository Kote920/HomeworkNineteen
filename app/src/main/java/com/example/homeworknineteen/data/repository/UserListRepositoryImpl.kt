package com.example.homeworknineteen.data.repository

import android.util.Log.d
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.mapper.todomain
import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.data.service.UserListService
import com.example.homeworknineteen.domain.model.UserListResponse
import com.example.homeworknineteen.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val userListService: UserListService,

    ) : UserListRepository {
    override suspend fun getList(): Flow<Resource<UserListResponse>> {

        return flow {
            try {
                emit(Resource.Loading())

                val response = userListService.getUsersList("7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")

                if (response.isSuccessful) {

                    val userList = response.body()
                    emit(Resource.Success(userList!!.todomain()))
                } else {
                    emit(Resource.Failed("Request Failed"))


                }

            } catch (e: Exception) {

                emit(Resource.Failed("Request Failed"))
                d("catcheddone", e.toString())

            }

        }
    }


}