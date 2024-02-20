package com.maxi.petzify.data.network.response.postsResponses.AllPosts


import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.post.Post

data class AllPostsResponse(
    @SerializedName("findPosts")
    val findPosts: List<FindPost>
){
    fun toDomain():List<Post> = findPosts.map { it.toDomain() }
}