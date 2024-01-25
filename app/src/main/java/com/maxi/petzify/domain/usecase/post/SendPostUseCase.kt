package com.maxi.petzify.domain.usecase.post

import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.PostsRepository
import com.maxi.petzify.domain.model.post.PostToSend
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class SendPostUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(post:PostToSend): Flow<ResponseState<String>> = flow {

        emit(ResponseState.Loading<String>())

        val data = repository.sendPost(post)

        if (data != null) {
            emit(ResponseState.Success<String>(data))
        } else {
            emit(ResponseState.Error<String>("Revisar los datos."))
        }

    }
}