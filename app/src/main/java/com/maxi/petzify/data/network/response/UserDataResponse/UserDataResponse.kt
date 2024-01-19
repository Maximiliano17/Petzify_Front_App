package com.maxi.petzify.data.network.response.UserDataResponse

import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.userData.UserData

data class UserDataResponse(
    @SerializedName("_id") val _id: String,
    @SerializedName("banner") val banner: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("email") val email: String,
    @SerializedName("followers") val followers: List<Any>,
    @SerializedName("following") val following: List<Any>,
    @SerializedName("isVerified") val isVerified: Boolean,
    @SerializedName("likes") val likes: List<Any>,
    @SerializedName("password") val password: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("role") val role: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("username") val username: String
) {
    fun toDomain(): UserData {
        return UserData(
            _id = _id,
            banner = banner,
            createdAt = createdAt,
            email = email,
            followers = followers,
            following = following,
            isVerified = isVerified,
            likes = likes,
            password = password,
            profile = profile,
            role = role,
            updatedAt = updatedAt,
            username = username
        )
    }
}