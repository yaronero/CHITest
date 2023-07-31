package com.example.chitest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chitest.data.model.UserDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM ${UserDbModel.TABLE_NAME}")
    fun getAllUsers(): Flow<List<UserDbModel>>

    @Query("SELECT * FROM ${UserDbModel.TABLE_NAME} WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserDbModel)

    @Update
    fun updateUser(user: UserDbModel)

    @Delete
    fun deleteUser(user: UserDbModel)
}