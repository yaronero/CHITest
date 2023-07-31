package com.example.chitest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chitest.data.model.UserDbModel

@Database(entities = [UserDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {
        const val DATABASE_NAME = "level2"
    }
}