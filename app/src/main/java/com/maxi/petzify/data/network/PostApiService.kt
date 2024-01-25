package com.maxi.petzify.data.network

import com.maxi.petzify.data.network.response.Post.PostResponse
import com.maxi.petzify.domain.model.post.PostToSend
import retrofit2.http.Body
import retrofit2.http.POST

interface PostApiService {
    @POST("api/v1/post/send/post")
    suspend fun sendPopst(@Body post:PostToSend): PostResponse
}