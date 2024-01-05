package com.example.homeworknineteen.data.service

import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.data.model.UserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UserListService {

    @GET
    suspend fun getUsersList(@Url url: String): Response<UserListResponseDto>
}