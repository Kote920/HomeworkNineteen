package com.example.homeworknineteen.di

import com.example.homeworknineteen.data.common.HandleResponse
import com.example.homeworknineteen.data.repository.UsersRepositoryImpl
import com.example.homeworknineteen.data.repository.UserRepositoryImpl
import com.example.homeworknineteen.data.service.UsersService
import com.example.homeworknineteen.data.service.UserService
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideUserListRepository(usersService: UsersService, handleResponse: HandleResponse): UsersRepository {
        return UsersRepositoryImpl(usersService, handleResponse)
    }
    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService, handleResponse: HandleResponse): UserRepository {
        return UserRepositoryImpl(userService, handleResponse)
    }

}