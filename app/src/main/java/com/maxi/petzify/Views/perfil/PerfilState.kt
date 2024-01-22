package com.maxi.petzify.Views.perfil

import com.maxi.petzify.domain.model.userData.UserData

data class PerfilState (
    val isLoading : Boolean = false,
    val userData : UserData? = null,
    val error : String = ""
)