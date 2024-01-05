package com.example.homeworknineteen.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.domain.model.UserListResponse
import com.example.homeworknineteen.domain.model.UserResponse
import com.example.homeworknineteen.domain.repository.UserListRepository
import com.example.homeworknineteen.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val repository: UserRepository): ViewModel() {


    private val _userFlow = MutableStateFlow<Resource<UserResponseDto>>(
        Resource.Success(
            UserResponseDto()
        )
    )
    val userFlow: StateFlow<Resource<UserResponseDto>> = _userFlow.asStateFlow()


    fun getUser(id: Int) {
        viewModelScope.launch {
            repository.getUser(id.toString()).collect() {

                when (it) {
                    is Resource.Loading -> _userFlow.value =Resource.Loading()
                    is Resource.Success -> _userFlow.value = Resource.Success(it.responseData)
                    is Resource.Failed -> _userFlow.value = Resource.Failed(it.message)
                }
            }
        }
    }
}