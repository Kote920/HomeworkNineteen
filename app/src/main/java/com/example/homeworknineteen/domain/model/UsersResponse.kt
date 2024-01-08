package com.example.homeworknineteen.domain.model

    data class UsersResponse(
        var id: Int? = null,
        var email: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var selected: Boolean = false
    )