package com.maxi.petzify.data.repository

import android.util.Log
import com.maxi.petzify.Petzify.Companion.pref
import com.maxi.petzify.data.network.GetUserApiService
import com.maxi.petzify.domain.UserRepository
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.code.Code
import com.maxi.petzify.domain.model.token.Token
import com.maxi.petzify.domain.model.userData.UserData
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val apiService: GetUserApiService) :
    UserRepository {
    override suspend fun login(userData: LoginDataRequired): Token? {
        runCatching { apiService.login(userData) }
            .onSuccess {
                it.body()?.token?.let { it1 -> pref.saveData("token", it1) }
                return it.body()?.toDomain()
            }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

    override suspend fun reciveCode(userData: LoginDataRequired): Code? {
        runCatching { apiService.reciveCode(userData) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

    override suspend fun register(userData: LoginDataRequired): Code? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserData(): UserData? {
        runCatching { apiService.getUserdata() }
            .onSuccess {
                Log.i("recibido datos", it.toString())
                return it.toDomain()
            }
            .onFailure { Log.i("Error", "Ha ocurrido el siguiente error: ${it.message}") }
        return null
    }
}