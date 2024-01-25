package com.maxi.petzify.Views.posteos

data class PostSendState(
    val isLoading : Boolean = false,
    val postResult : String? = null,
    val error : String = ""
)