package com.maxi.petzify.domain.model.registerResponse

data class RegisterResult(
    val accepted: List<String>,
    val ehlo: List<String>,
    val envelopeTime: Int,
    val messageId: String,
    val messageSize: Int,
    val messageTime: Int,
    val rejected: List<Any>,
    val response: String
)