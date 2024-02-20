package com.maxi.petzify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.usecase.post.GetAllPostsUseCase
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllPostsUseCase: GetAllPostsUseCase) :
    ViewModel() {
    private val postsListValue = MutableStateFlow(PostsState())
    var _postsListValue: StateFlow<PostsState> = postsListValue

    fun getAllPosts(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        getAllPostsUseCase(page).collect {
            Logger.d("aqui")
            when (it) {
                is ResponseState.Success -> {
                    postsListValue.value = PostsState(postsList = it.data ?: emptyList())
                }

                is ResponseState.Error -> {
                    postsListValue.value = PostsState(isLoading = true)
                }

                is ResponseState.Loading -> {
                    postsListValue.value = PostsState(isLoading = true)
                }
            }
        }
    }
}