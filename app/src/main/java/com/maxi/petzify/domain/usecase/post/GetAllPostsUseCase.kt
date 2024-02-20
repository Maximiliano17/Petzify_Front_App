package com.maxi.petzify.domain.usecase.post

import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.PostsRepository
import com.maxi.petzify.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(page: Int): Flow<ResponseState<List<Post>>> = flow {
        emit(ResponseState.Loading<List<Post>>())
        val posts = repository.getAllPosts(page)
        if (posts.isNullOrEmpty()) {
            emit(ResponseState.Error<List<Post>>("An Unexpected Error"))
        } else {
            emit(ResponseState.Success<List<Post>>(posts))
        }
    }
}