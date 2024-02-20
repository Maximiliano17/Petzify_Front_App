package com.maxi.petzify.ui.posteos

data class PostSendState(
    val isLoading : Boolean = false,
    val postResult : String? = null,
    val error : String = ""
)