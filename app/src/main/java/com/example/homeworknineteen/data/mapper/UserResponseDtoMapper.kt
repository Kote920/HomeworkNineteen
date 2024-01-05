package com.example.homeworknineteen.data.mapper

import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.domain.model.UserListResponse
import com.example.homeworknineteen.domain.model.UserResponse

fun UserResponseDto.todomain(): UserResponse {

    return UserResponse(id, email, firstName, lastName, avatar)

}