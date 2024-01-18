package com.maxi.petzify.domain

import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.registerResponse.RegisterResult
import com.maxi.petzify.domain.model.user.User
import com.maxi.petzify.domain.model.userdata.UserData

interface UserRepository {
    suspend fun login(userData: LoginDataRequired):UserData?
    suspend fun register(userData: LoginDataRequired):RegisterResult?

}