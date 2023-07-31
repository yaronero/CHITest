package com.example.chitest.domain.repositories

import com.example.chitest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getAllUsers(): Flow<List<User>>

    suspend fun getUserById(userId: Int): Flow<User>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)
}