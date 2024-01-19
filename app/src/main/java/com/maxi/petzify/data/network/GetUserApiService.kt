package com.maxi.petzify.data.network

import com.maxi.petzify.data.network.response.Login.LoginResponse
import com.maxi.petzify.data.network.response.ReciveCode.ReciveCodeResponse
import com.maxi.petzify.domain.model.LoginDataRequired
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetUserApiService {
    @POST("api/v1/auth/signin")
    suspend fun login(@Body user: LoginDataRequired):Response<LoginResponse>

    @POST("api/v1/auth/code")
    suspend fun reciveCode(@Body user: LoginDataRequired):ReciveCodeResponse
    @POST("api/v1/auth/signup")
    suspend fun register(@Body user:LoginDataRequired):ReciveCodeResponse

}