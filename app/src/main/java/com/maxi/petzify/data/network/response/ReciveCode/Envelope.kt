package com.maxi.petzify.data.network.response.ReciveCode

import com.google.gson.annotations.SerializedName

data class Envelope(
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: List<String>
)