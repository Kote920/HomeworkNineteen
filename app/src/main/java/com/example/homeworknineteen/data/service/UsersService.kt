package com.example.homeworknineteen.data.service

import com.example.homeworknineteen.data.model.UsersResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface UsersService {

    @GET("7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUsersList(): Response<List<UsersResponseDto>>

    @DELETE("https://reqres.in/api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>
}