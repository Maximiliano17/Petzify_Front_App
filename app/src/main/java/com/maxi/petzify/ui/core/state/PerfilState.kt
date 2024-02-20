package com.maxi.petzify.ui.core.state

import com.maxi.petzify.domain.model.userData.UserData

data class PerfilState (
    val isLoading : Boolean = false,
    val userData : UserData? = null,
    val error : String = ""
)