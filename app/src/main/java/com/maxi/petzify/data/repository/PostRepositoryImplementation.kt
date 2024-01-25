package com.maxi.petzify.data.repository

import android.util.Log
import com.maxi.petzify.Petzify
import com.maxi.petzify.data.network.PostApiService
import com.maxi.petzify.domain.PostsRepository
import com.maxi.petzify.domain.model.post.PostToSend
import javax.inject.Inject

class PostRepositoryImplementation @Inject constructor(private val apiService: PostApiService) : PostsRepository {
    override suspend fun sendPost(post: PostToSend): String? {
        runCatching { apiService.sendPopst(post) }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }
}