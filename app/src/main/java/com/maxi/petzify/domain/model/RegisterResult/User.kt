package com.maxi.petzify.domain.model.RegisterResult

data class User(
    val username: String,
    val email: String?,
    val password: String,
    val role: String?
)