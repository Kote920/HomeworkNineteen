package com.example.homeworknineteen.data.repository

import com.example.homeworknineteen.data.common.HandleResponse
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.mapper.toDomain
import com.example.homeworknineteen.data.service.UsersService
import com.example.homeworknineteen.domain.model.DeleteResponse
import com.example.homeworknineteen.domain.model.DeleteResult
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersService: UsersService,
    private val handleResponse: HandleResponse

) : UsersRepository {
    override suspend fun getList(): Flow<Resource<List<UsersResponse>>> {
        return handleResponse.safeApiCall {
            usersService.getUsersList()
        }.map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.responseData.map { it.toDomain() })
                is Resource.Failed -> Resource.Failed(res.message)
                is Resource.Loading -> Resource.Loading()
            }
        }


    }

    override suspend fun deleteUsers(id: String): Flow<Resource<DeleteResponse>> {
//        return handleResponse.safeApiCall {
//            usersService.deleteUser(id)
//        }.map { res ->
//            when (res) {
//                is Resource.Success -> Resource.Success(DeleteResponse(DeleteResult.SUCCESSFUL))
//                is Resource.Failed -> {Resource.Failed(res.message)}
//                is Resource.Loading -> Resource.Loading()
//            }
//        }
        return flow{
            try {
                emit(Resource.Loading())
                val response = usersService.deleteUser(id)

                if(response.isSuccessful){
                    emit(Resource.Success(DeleteResponse(DeleteResult.SUCCESSFUL)))
                }else{
                    emit(Resource.Failed("Request failed"))
                }

            }catch (e: Exception){
                emit(Resource.Failed("Request failed!"))
            }
        }
    }


}