package com.maxi.petzify.domain.model

data class LoginDataRequired(
    val username:String,
    val mail:String?,
    val password:String,
    val role:String?
)