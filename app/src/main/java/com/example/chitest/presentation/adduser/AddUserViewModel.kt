package com.example.chitest.presentation.adduser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitest.domain.model.User
import com.example.chitest.domain.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _user = MutableStateFlow(
        User(
            name = "",
            age = 0,
            birthOfDate = Date().time
        )
    )
    val user: StateFlow<User> = _user.asStateFlow()

    fun updateName(name: String) {
        _user.value = _user.value.copy(
            name = name
        )
    }

    fun updateAge(age: Int) {
        _user.value = _user.value.copy(
            age = age
        )
    }

    fun updateBirthOfDate(date: Long) {
        _user.value = _user.value.copy(
            birthOfDate = date
        )
    }

    fun updateIsStudent(isStudent: Boolean) {
        _user.value = _user.value.copy(
            isStudent = isStudent
        )
    }

    fun createUser() {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.addUser(user.value)
        }
    }
}