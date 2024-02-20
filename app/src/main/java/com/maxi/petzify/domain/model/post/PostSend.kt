package com.maxi.petzify.domain.model.post

data class PostToSend (
    val username: String?,
    val user: String?,
    val title: String,
    val desc: String,
    val image: String
)