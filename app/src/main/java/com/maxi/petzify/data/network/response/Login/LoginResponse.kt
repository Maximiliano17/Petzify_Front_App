package com.maxi.petzify.data.network.response.Login

import com.maxi.petzify.domain.model.userdata.UserData

data class LoginResponse(
    val status: Int,
    val token: String,
    val user: User
){
    fun toDomain():UserData{
        return UserData(
        _id = user._id,
        banner = user.banner,
        createdAt = user.createdAt,
        email = user.email,
        followers = user.followers,
        following = user.following,
        isVerified = user.isVerified,
        likes = user.likes,
        password = user.password,
        profile = user.profile,
        role = user.role,
        updatedAt = user.updatedAt,
        username = user.username
        )
    }
}