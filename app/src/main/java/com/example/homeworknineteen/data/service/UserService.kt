package com.example.homeworknineteen.data.service

import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.domain.model.UserResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Url

interface UserService {

    @GET
    suspend fun getUser(@Url url: String): Response<UserResponseDto>



}