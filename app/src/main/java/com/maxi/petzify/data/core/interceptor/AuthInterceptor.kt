package com.maxi.petzify.data.core.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager:TokenManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //volver a crear la peticion, pero con informacion agregada por mi
        val request: Request = chain.request()
            .newBuilder()
            .header("Autorization", "valor")
            .build()
        return chain.proceed(request)
    }

}
class TokenManager @Inject constructor(){
    fun getToken():String = "este es el token"
}