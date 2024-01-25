package com.maxi.petzify.data.network.response.Post


import com.google.gson.annotations.SerializedName
data class PostResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("newPost")
    val newPost: NewPost
){
    fun toDomain() = message
}