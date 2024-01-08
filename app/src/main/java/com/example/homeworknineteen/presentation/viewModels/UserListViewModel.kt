package com.example.homeworknineteen.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.domain.model.UsersResponse
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.useCase.GetUsersListUseCase
import com.example.homeworknineteen.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUsersListUseCase: GetUsersListUseCase) : ViewModel() {

    private val _userListFlow = MutableStateFlow<Resource<List<User>>>(
        Resource.Success(
            emptyList()
        )
    )

    val userListFlow: StateFlow<Resource<List<User>>> = _userListFlow.asStateFlow()

    fun getList() {
        viewModelScope.launch {
            getUsersListUseCase.execute().collect() {

                when (it) {
                    is Resource.Loading -> _userListFlow.value =Resource.Loading()
                    is Resource.Success -> _userListFlow.value = Resource.Success(it.responseData)
                    is Resource.Failed -> _userListFlow.value = Resource.Failed(it.message)
                }
            }
        }
    }


}