package com.example.homeworknineteen.di

import com.example.homeworknineteen.domain.repository.UserRepository
import com.example.homeworknineteen.domain.repository.UsersRepository
import com.example.homeworknineteen.domain.useCase.GetUserDetailsUseCase
import com.example.homeworknineteen.domain.useCase.GetUsersListUseCase
import com.example.homeworknineteen.domain.useCaseImpl.GetUserDetailsUseCaseImpl
import com.example.homeworknineteen.domain.useCaseImpl.GetUsersListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetUsersListUseCase(usersRepository: UsersRepository): GetUsersListUseCase {
        return GetUsersListUseCaseImpl(usersRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailsUseCase(userRepository: UserRepository): GetUserDetailsUseCase {
        return GetUserDetailsUseCaseImpl(userRepository)
    }
}