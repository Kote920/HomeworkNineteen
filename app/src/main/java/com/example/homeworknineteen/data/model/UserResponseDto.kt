package com.example.homeworknineteen.data.model


data class UserResponseDto(
    val data: UsersResponseDto? = null,
    val support: SupportData? = null
)
data class SupportData(
    val url: String,
    val text: String
)

