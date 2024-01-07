package com.maxi.petzify.Api

import com.maxi.petzify.Api.Models.Login.LoginResponse
import com.maxi.petzify.Api.Models.Register.RegisterResponse
import com.maxi.petzify.Api.Models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConsumirApi {
    @POST("api/v1/auth/signin")
    suspend fun login(@Body user: User):Response<LoginResponse>

    @POST("api/v1/auth/code")
    suspend fun register(@Body user: User):Response<RegisterResponse>
}