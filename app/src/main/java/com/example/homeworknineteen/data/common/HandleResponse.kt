package com.example.homeworknineteen.data.common

import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleResponse {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = flow<Resource<T>> {
        emit(Resource.Loading())
        try {

            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body))
            } else {
                emit(Resource.Failed("Request failed"))
            }

        } catch (e: Throwable) {
            emit(Resource.Failed(e.message ?: ""))
        }
    }


}
