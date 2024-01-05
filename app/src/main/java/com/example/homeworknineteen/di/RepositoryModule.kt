package com.example.homeworknineteen.di

import com.example.homeworknineteen.data.repository.UserListRepositoryImpl
import com.example.homeworknineteen.data.repository.UserRepositoryImpl
import com.example.homeworknineteen.data.service.UserListService
import com.example.homeworknineteen.data.service.UserService
import com.example.homeworknineteen.domain.repository.UserListRepository
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
    fun provideUserListRepository(userListService: UserListService): UserListRepository {
        return UserListRepositoryImpl(userListService)
    }
    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepositoryImpl(userService)
    }

}