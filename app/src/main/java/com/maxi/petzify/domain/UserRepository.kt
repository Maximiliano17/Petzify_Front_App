package com.maxi.petzify.domain

import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.code.Code
import com.maxi.petzify.domain.model.token.Token
import com.maxi.petzify.domain.model.userData.UserData

interface UserRepository {
    suspend fun login(userData: LoginDataRequired):Token?
    suspend fun reciveCode(userData: LoginDataRequired):Code?
    suspend fun register(userData: LoginDataRequired):Code?

    suspend fun getUserData():UserData?
}