package com.maxi.petzify.data.network.response.postsResponses.AllPosts


import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.post.Post

data class FindPost(
    @SerializedName("comments")
    val comments: List<Any>,
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
) {
    fun toDomain() =
        Post(
            comments = comments,
            createdAt = createdAt,
            desc = desc,
            id = id,
            image = image,
            room = room,
            title = title,
            updatedAt = updatedAt,
            user = user,
            username = username
        )
}