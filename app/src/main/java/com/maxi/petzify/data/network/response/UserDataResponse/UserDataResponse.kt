package com.maxi.petzify.data.network.response.UserDataResponse


import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.userData.UserData

data class UserDataResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
){
    fun toDomain(): UserData {
        return UserData(
            _id = user.id,
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