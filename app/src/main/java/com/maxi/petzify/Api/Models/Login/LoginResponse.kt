package com.maxi.petzify.Api.Models.Login

data class LoginResponse(
    val status: Int,
    val token: String,
    val user: User
)