package com.maxi.petzify.data.network.response.Register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)