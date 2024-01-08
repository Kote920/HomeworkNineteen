package com.example.homeworknineteen.data.mapper

import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.domain.model.UserResponse

fun UserResponseDto.toDomain(): UserResponse?{
    return if (data!= null){
        UserResponse(data.toDomain())
    }else{
        UserResponse(null)
    }
}