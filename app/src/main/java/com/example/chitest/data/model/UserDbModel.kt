package com.example.chitest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDbModel.TABLE_NAME)
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val birthOfDate: Long,
    val isStudent: Boolean
) {
    companion object {
        const val TABLE_NAME = "users"
    }
}
