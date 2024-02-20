package com.maxi.petzify.data.network.response.postsResponses.DeletePostResponse

import com.google.gson.annotations.SerializedName

data class DeletePostResponse(
    @SerializedName("deleted")
    val deleted: Deleted,
    @SerializedName("message")
    val message: String
)