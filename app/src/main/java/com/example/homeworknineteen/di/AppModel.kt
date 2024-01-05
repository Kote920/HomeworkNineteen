package com.example.homeworknineteen.di

import com.example.homeworknineteen.data.service.UserListService
import com.example.homeworknineteen.data.service.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    const val BASE_URL_USER_LIST = "https://run.mocky.io/v3/"
    const val BASE_URL_USER = "'https://reqres.in/api/users/"

    @Singleton
    @Provides
    @Named("userListRetrofit")
    fun provideListRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USER_LIST)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()

    }

    @Singleton
    @Provides
    @Named("UserRetrofit")
    fun provideUserRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()

    }

    @Singleton
    @Provides
 fun provideUsersListService(@Named("userListRetrofit") retrofit: Retrofit): UserListService {

        return retrofit.create(UserListService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(@Named("UserRetrofit") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}