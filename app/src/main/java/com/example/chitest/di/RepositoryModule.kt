package com.example.chitest.di

import com.example.chitest.data.repositories.UsersRepositoriesImpl
import com.example.chitest.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindUsersRepository(repositoryImpl: UsersRepositoriesImpl): UsersRepository
}