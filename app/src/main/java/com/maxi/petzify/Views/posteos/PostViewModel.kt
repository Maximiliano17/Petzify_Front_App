package com.maxi.petzify.Views.posteos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.petzify.Views.core.state.PerfilState
import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.model.post.PostToSend
import com.maxi.petzify.domain.usecase.ReciveUserData
import com.maxi.petzify.domain.usecase.post.SendPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(private val sendPostUseCase: SendPostUseCase) :
    ViewModel() {
    private var postResponse = MutableStateFlow(PostSendState())
    var _postResponse: StateFlow<PostSendState> = postResponse

    fun uploadPost(post: PostToSend) = viewModelScope.launch(Dispatchers.IO) {
        sendPostUseCase(post).collect{
            when (it) {
                is ResponseState.Success -> {
                    postResponse.value = PostSendState(postResult = it.data)
                }

                is ResponseState.Loading -> {
                    postResponse.value = PostSendState(isLoading = true)
                }
                is ResponseState.Error -> {
                    postResponse.value = PostSendState(error = "Ocurrio un error al subir un post")
                }
            }
        }
    }
}