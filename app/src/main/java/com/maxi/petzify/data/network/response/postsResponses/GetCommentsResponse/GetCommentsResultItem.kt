package com.maxi.petzify.data.network.response.postsResponses.GetCommentsResponse


import com.google.gson.annotations.SerializedName

data class GetCommentsResultItem(
    @SerializedName("comments")
    val comments: List<Comment>,
    @SerializedName("_id")
    val id: String
)