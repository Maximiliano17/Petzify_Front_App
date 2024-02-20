package com.maxi.petzify.data.network

import com.maxi.petzify.data.network.response.postsResponses.AllPosts.AllPostsResponse
import com.maxi.petzify.data.network.response.postsResponses.DeletePostResponse.DeletePostResponse
import com.maxi.petzify.data.network.response.postsResponses.GetCommentsResponse.GetCommentsResult
import com.maxi.petzify.data.network.response.postsResponses.SendPost.PostResponse
import com.maxi.petzify.data.network.response.postsResponses.uploadCommentResponse.UploadCommentResponse
import com.maxi.petzify.domain.model.comments.CommentToSend
import com.maxi.petzify.domain.model.post.PostToSend
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiService {
    @POST("api/v1/post/send/post")
    suspend fun sendPopst(@Body post:PostToSend): PostResponse

    @GET("api/v1/post/all/post/{page}")
    suspend fun getAllPosts(@Path("page") page:Int):Response<AllPostsResponse>

    @GET("api/v1/post/all/comment/{room}")
    suspend fun getComents(@Path("room") room: String):Response<GetCommentsResult>

    @POST("api/v1/post/send/comment")
    suspend fun uploadComment(@Body comment:CommentToSend):Response<UploadCommentResponse>

    @DELETE("api/v1/post/p/{room}")
    suspend fun deletePost(@Path("room") room:String):Response<DeletePostResponse>
}