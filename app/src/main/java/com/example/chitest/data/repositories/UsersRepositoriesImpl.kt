package com.example.chitest.data.repositories

import com.example.chitest.data.database.UsersDao
import com.example.chitest.data.mappers.UserMapper
import com.example.chitest.domain.model.User
import com.example.chitest.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UsersRepositoriesImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val userMapper: UserMapper
) : UsersRepository {

    override suspend fun getAllUsers(): Flow<List<User>> {
        return usersDao.getAllUsers().transform {
            emit(userMapper.mapUserDbEntityListToUserModelList(it))
        }
    }

    override suspend fun getUserById(userId: Int): Flow<User> {
        return usersDao.getUserById(userId).transform {
            emit(userMapper.mapUserDbEntityToUserModel(it))
        }
    }

    override suspend fun addUser(user: User) {
        usersDao.addUser(userMapper.mapUserModelToUserDbEntity(user))
    }

    override suspend fun updateUser(user: User) {
        usersDao.updateUser(userMapper.mapUserModelToUserDbEntity(user))
    }

    override suspend fun deleteUser(user: User) {
        usersDao.deleteUser(userMapper.mapUserModelToUserDbEntity(user))
    }
}