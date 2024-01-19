package com.maxi.petzify.data.network.response.Register


import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("followers")
    val followers: List<Any>,
    @SerializedName("following")
    val following: List<Any>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("isVerified")
    val isVerified: Boolean,
    @SerializedName("likes")
    val likes: List<Any>,
    @SerializedName("password")
    val password: String,
    @SerializedName("profile")
    val profile: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)