package com.example.homeworknineteen.domain.mapper

import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.presentation.model.User

fun UsersResponse.toPresentation(): User {

    return User(id, email, firstName, lastName, avatar)

}