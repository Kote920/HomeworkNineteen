package com.example.homeworknineteen.presentation.viewModels

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.DeleteResponse
import com.example.homeworknineteen.domain.model.DeleteResult
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.useCase.DeleteUserUseCase
import com.example.homeworknineteen.domain.useCase.GetUsersListUseCase
import com.example.homeworknineteen.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val getDeleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _userListFlow = MutableStateFlow<Resource<List<User>>>(
        Resource.Success(
            emptyList()
        )
    )

    val userListFlow: StateFlow<Resource<List<User>>> = _userListFlow.asStateFlow()

    private val _deleteListFlow = MutableSharedFlow<Resource<DeleteResponse>>()

    val deleteListFlow: SharedFlow<Resource<DeleteResponse>> = _deleteListFlow.asSharedFlow()


    fun getList() {
        viewModelScope.launch {
            getUsersListUseCase.execute().collect() {

                when (it) {
                    is Resource.Loading -> _userListFlow.value = Resource.Loading()
                    is Resource.Success -> _userListFlow.value = Resource.Success(it.responseData)
                    is Resource.Failed -> _userListFlow.value = Resource.Failed(it.message)
                }
            }
        }
    }

    fun deleteUser(list: List<Int>) {
        viewModelScope.launch {
            for (id in list) {
                getDeleteUserUseCase.execute(id).collect() {
                    when (it) {
                        is Resource.Loading -> _deleteListFlow.emit(Resource.Loading())
                        is Resource.Success ->{ _deleteListFlow.emit(
                            Resource.Success(it.responseData)
                        )
                            d("User Removed","Successfully")}


                        is Resource.Failed -> {_deleteListFlow.emit(Resource.Failed(it.message))
                            d("User Removed",it.message)}
                    }
                }
            }
        }

    }


}