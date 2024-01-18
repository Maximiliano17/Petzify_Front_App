package com.maxi.petzify.domain.model.user

data class User(
    val username: String,
    val email: String?,
    val password: String,
    val role: String?
)