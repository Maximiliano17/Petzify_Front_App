package com.maxi.petzify.data.network.response.postsResponses.uploadCommentResponse

import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("comments")
    val comments: List<Comment>,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("image")
    val image: List<String>,
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