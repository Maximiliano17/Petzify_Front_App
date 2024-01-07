package com.maxi.petzify.Api.Models.Register

data class Result(
    val accepted: List<String>,
    val ehlo: List<String>,
    val envelope: Envelope,
    val envelopeTime: Int,
    val messageId: String,
    val messageSize: Int,
    val messageTime: Int,
    val rejected: List<Any>,
    val response: String
)