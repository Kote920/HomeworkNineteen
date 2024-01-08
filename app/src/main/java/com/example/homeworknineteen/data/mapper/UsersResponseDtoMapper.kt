package com.example.homeworknineteen.data.mapper

import com.example.homeworknineteen.data.model.UsersResponseDto

import com.example.homeworknineteen.domain.model.UsersResponse

fun UsersResponseDto.toDomain(): UsersResponse {

    return UsersResponse(id, email, firstName, lastName, avatar)

}