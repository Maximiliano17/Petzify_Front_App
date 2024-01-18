package com.maxi.petzify.data

import android.util.Log
import com.maxi.petzify.data.network.GetUserApiService
import com.maxi.petzify.domain.UserRepository
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.registerResponse.RegisterResult
import com.maxi.petzify.domain.model.userdata.UserData
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val apiService: GetUserApiService) :
    UserRepository {
    override suspend fun login(userData: LoginDataRequired): UserData? {
        runCatching { apiService.login(userData) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

    override suspend fun register(userData: LoginDataRequired): RegisterResult?{
        runCatching { apiService.register(userData) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("yo", "Ha ocurrido un error: ${it.message}") }
        return null
    }

}