package com.maxi.petzify.domain

import com.maxi.petzify.domain.model.post.Post
import com.maxi.petzify.domain.model.post.PostToSend
interface PostsRepository {
    suspend fun sendPost(post: PostToSend):String?
    suspend fun getAllPosts(page:Int):List<Post>?
}