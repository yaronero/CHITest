package com.example.chitest.data.mappers

import com.example.chitest.data.model.UserDbModel
import com.example.chitest.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun mapUserDbEntityListToUserModelList(users: List<UserDbModel>): List<User> {
        return users.map { mapUserDbEntityToUserModel(it) }
    }

    fun mapUserDbEntityToUserModel(user: UserDbModel): User {
        return User(
            id = user.id,
            name = user.name,
            age = user.age,
            birthOfDate = user.birthOfDate,
            isStudent = user.isStudent
        )
    }

    fun mapUserModelToUserDbEntity(user: User): UserDbModel {
        return UserDbModel(
            id = user.id,
            name = user.name,
            age = user.age,
            birthOfDate = user.birthOfDate,
            isStudent = user.isStudent
        )
    }
}