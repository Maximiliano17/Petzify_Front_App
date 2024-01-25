package com.maxi.petzify.Views.core.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.petzify.Views.core.state.PerfilState
import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.usecase.ReciveUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel  @Inject constructor(private val reciveUserDataUseCase: ReciveUserData) :
    ViewModel() {
    private var userData = MutableStateFlow(PerfilState())
    var _userData: StateFlow<PerfilState> = userData

    init {
        getUserData()
    }
    fun getUserData() = viewModelScope.launch(Dispatchers.IO) {
        reciveUserDataUseCase().collect {
            when (it) {
                is ResponseState.Success -> {
                    userData.value = PerfilState(userData = it.data)
                }

                is ResponseState.Loading -> {
                    userData.value = PerfilState(isLoading = true)
                }

                is ResponseState.Error -> {
                    userData.value = PerfilState(error = it.message ?: "Ocurrio un error")
                }
            }
        }
    }
}