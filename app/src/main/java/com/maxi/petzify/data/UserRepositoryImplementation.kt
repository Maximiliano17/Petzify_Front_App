package com.maxi.petzify.data

import android.util.Log
import com.maxi.petzify.data.network.GetUserApiService
import com.maxi.petzify.domain.UserRepository
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.code.Code
import com.maxi.petzify.domain.model.token.Token
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val apiService: GetUserApiService) :
    UserRepository {
    override suspend fun login(userData: LoginDataRequired): Token? {
        runCatching { apiService.login(userData) }
            .onSuccess {
                return it.body()?.toDomain()
            }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

    override suspend fun reciveCode(userData: LoginDataRequired): Code?{
        runCatching { apiService.reciveCode(userData) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

    override suspend fun register(userData: LoginDataRequired): Code? {
        TODO("Not yet implemented")
    }

}