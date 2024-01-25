package com.maxi.petzify.domain

import com.maxi.petzify.domain.model.token.Token
import com.maxi.petzify.domain.model.userData.UserData

interface LocalRepository {
    fun getLocalToken(): String?
    fun getUsername(): String?
    fun getUserId():String?

}