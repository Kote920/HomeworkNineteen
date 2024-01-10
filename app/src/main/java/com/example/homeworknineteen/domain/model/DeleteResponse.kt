package com.example.homeworknineteen.domain.model

data class DeleteResponse(var result: DeleteResult)

enum class DeleteResult(){
    SUCCESSFUL,
    FAILED

}