package com.maxi.petzify.data.core.interceptor

import com.maxi.petzify.Petzify.Companion.pref
import com.maxi.petzify.domain.model.token.Token
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

class AuthInterceptor @Inject constructor(private val tokenManager:TokenManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //volver a crear la peticion, pero con informacion agregada por mi
        val request: Request = chain.request()
            .newBuilder()
            .header("auth-token", tokenManager.getToken())
            .build()
        return chain.proceed(request)
    }

}
@Singleton
class TokenManager @Inject constructor(){
    fun getToken() = pref.getSring("token")
}