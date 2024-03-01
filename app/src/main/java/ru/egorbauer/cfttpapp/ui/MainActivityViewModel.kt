package ru.egorbauer.cfttpapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import ru.egorbauer.cfttpapp.domain.repo.IUserRepo
import ru.egorbauer.cfttpapp.domain.entity.User

class MainActivityViewModel(
    private val userRepo: IUserRepo
) : ViewModel() {
    private val _userStateFlow = MutableStateFlow<List<User>?>(null)
    val userStateFlow = _userStateFlow.asStateFlow().filterNotNull()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            val users = userRepo.getUser()
            _userStateFlow.emit(users)
        }
    }

    fun updateUsers() {
        viewModelScope.launch {
            val users = userRepo.updateList()
            _userStateFlow.emit(users)
        }
    }
}