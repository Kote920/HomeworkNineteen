package com.example.homeworknineteen.data.model

import com.example.homeworknineteen.domain.model.UserResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserListResponseDto(var userList: List<UserResponseDto>? = null) {
}