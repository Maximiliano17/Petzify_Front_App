package com.maxi.petzify.ui.home

import com.maxi.petzify.domain.model.post.Post

data class PostsState(
    val isLoading : Boolean = false,
    val postsList : List<Post> = emptyList(),
    val error : String = ""
)
