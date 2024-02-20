package com.maxi.petzify.domain.model.comments

data class CommentToSend(
    val comment: String,
    val room: String,
    val username: String
)