package com.example.chitest.domain.model

data class User(
    var id: Int = UNDEFINED_ID,
    val name: String,
    val age: Int,
    val birthOfDate: Long,
    var isStudent: Boolean = false
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
