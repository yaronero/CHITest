package com.example.chitest.presentation.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitest.domain.model.User
import com.example.chitest.domain.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _user = MutableSharedFlow<User>(replay = 1, 1, BufferOverflow.DROP_OLDEST)
    val user: SharedFlow<User> = _user.asSharedFlow()

    fun getUserById(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.getUserById(userId).collect {
                _user.emit(it)
            }
        }
    }
}