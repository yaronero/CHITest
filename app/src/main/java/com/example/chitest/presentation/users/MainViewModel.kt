package com.example.chitest.presentation.users

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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usersRepositories: UsersRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepositories.getAllUsers().collect {
                _users.value = it
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepositories.updateUser(user)
        }
    }
}