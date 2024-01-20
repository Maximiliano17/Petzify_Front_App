package com.maxi.petzify.data.network.response.Login

import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.token.Token

data class LoginResponse(
    @SerializedName("token")val token: String,
    @SerializedName("status")val status: Int,
    @SerializedName("message")val message: String,
){
    fun toDomain() = Token(token)
}