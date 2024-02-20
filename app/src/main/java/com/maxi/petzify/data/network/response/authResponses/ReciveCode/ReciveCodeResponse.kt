package com.maxi.petzify.data.network.response.authResponses.ReciveCode

import com.google.gson.annotations.SerializedName
import com.maxi.petzify.domain.model.code.Code

data class ReciveCodeResponse(
    @SerializedName("code") val code: String,
    @SerializedName("result") val result: Result
) {
    fun toDomain(): Code {
        return Code(
            code = code
        )
    }
}
