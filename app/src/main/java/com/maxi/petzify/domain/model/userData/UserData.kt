package com.maxi.petzify.domain.model.userData

import com.google.gson.annotations.SerializedName

data class UserData (
    val _id: String,
    val banner: String,
    val createdAt: String,
    val email: String,
    val followers: List<Any>,
    val following: List<Any>,
    val isVerified: Boolean,
    val likes: List<Any>,
    val password: String,
    val profile: String,
    val role: String,
    val updatedAt: String,
    val username: String
)