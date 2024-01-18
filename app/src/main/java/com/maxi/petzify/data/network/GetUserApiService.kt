package com.maxi.petzify.data.network

import com.maxi.petzify.data.network.response.Login.LoginResponse
import com.maxi.petzify.data.network.response.Register.RegisterResponse
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.user.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetUserApiService {
    @POST("api/v1/auth/signin")
    suspend fun login(@Body user: LoginDataRequired):LoginResponse

    @POST("api/v1/auth/code")
    suspend fun register(@Body user: LoginDataRequired):RegisterResponse
}