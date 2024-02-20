package com.maxi.petzify.data.network.response.postsResponses.DeletePostResponse

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("username")
    val username: String
)