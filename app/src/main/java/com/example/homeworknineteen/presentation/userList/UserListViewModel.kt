package com.example.homeworknineteen.presentation.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.model.UserListResponseDto
import com.example.homeworknineteen.domain.model.UserListResponse
import com.example.homeworknineteen.domain.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(val repository: UserListRepository) : ViewModel() {

    private val _userListFlow = MutableStateFlow<Resource<UserListResponse>>(
        Resource.Success(
            UserListResponse()
        )
    )

    val userListFlow: StateFlow<Resource<UserListResponse>> = _userListFlow.asStateFlow()

    fun getList() {
        viewModelScope.launch {
            repository.getList().collect() {

                when (it) {
                    is Resource.Loading -> _userListFlow.value =Resource.Loading()
                    is Resource.Success -> _userListFlow.value = Resource.Success(it.responseData)
                    is Resource.Failed -> _userListFlow.value = Resource.Failed(it.message)
                }
            }
        }
    }


}