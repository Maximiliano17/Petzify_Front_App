package com.maxi.petzify.data.network.response.postsResponses.SendPost


import com.google.gson.annotations.SerializedName

data class NewPost(
    @SerializedName("comments")
    val comments: List<Any>,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("image")
    val image: List<Any>,
    @SerializedName("room")
    val room: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user")
    val user: List<String>,
    @SerializedName("username")
    val username: String
)