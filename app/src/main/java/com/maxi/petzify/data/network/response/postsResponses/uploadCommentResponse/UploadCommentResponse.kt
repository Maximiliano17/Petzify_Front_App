package com.maxi.petzify.data.network.response.postsResponses.uploadCommentResponse

import com.google.gson.annotations.SerializedName

data class UploadCommentResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("username")
    val username: String
)