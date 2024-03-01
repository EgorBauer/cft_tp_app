package ru.egorbauer.cfttpapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import ru.egorbauer.cfttpapp.domain.repo.IUserRepo
import ru.egorbauer.cfttpapp.domain.entity.User

class SecondActivityViewModel(
    private val userRepo: IUserRepo
) : ViewModel() {
    private val _userSecondStateFlow = MutableStateFlow<List<User>?>(null)
    val userSecondStateFlow = _userSecondStateFlow.asStateFlow().filterNotNull()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            val users = userRepo.getUser()
            _userSecondStateFlow.emit(users)
        }
    }

}