package com.maxi.petzify.data.network.response.Register

import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.registerResponse.RegisterResult

data class RegisterResponse(
    @SerializedName("code") val code: String,
    @SerializedName("result") val result: Result
) {
    fun toDomain(): RegisterResult {
        return RegisterResult(
            accepted = result.accepted,
            ehlo = result.ehlo,
            envelopeTime = result.envelopeTime,
            messageId = result.messageId,
            messageSize = result.messageSize,
            messageTime = result.messageTime,
            rejected = result.rejected,
            response = result.response
        )
    }
}
