package com.example.homeworknineteen.domain.mapper

import com.example.homeworknineteen.data.model.UsersResponseDto
import com.example.homeworknineteen.domain.model.UserResponse
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.presentation.model.User

fun UserResponse.toPresentation(): User? {

    if (data != null) {
        return User(data.id, data.email, data.firstName, data.lastName, data.avatar)
    } else {
        return null
    }

}