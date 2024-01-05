package com.example.homeworknineteen.data.mapper

import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.domain.model.UserListResponse

fun UserListResponseDto.todomain(): UserListResponse{

    return UserListResponse(userList)

}