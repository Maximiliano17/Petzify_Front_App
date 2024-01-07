package com.maxi.petzify.Api.Models

data class User(
    val username: String,
    val email: String?,
    val password: String,
    val role: String?
)