package com.maxi.petzify.data.repository

import android.util.Log
import com.maxi.petzify.data.core.interceptor.TokenManager
import com.maxi.petzify.domain.LocalRepository
import javax.inject.Inject

class LocalRepositoryImplementation @Inject constructor(private val tokenManager: TokenManager) : LocalRepository {
    override fun getLocalToken(): String? {
        runCatching { tokenManager.getToken() }
            .onSuccess { return it }
            .onFailure { Log.i("Error", "Ha ocurrido un error al consultar el almacenamiento local.") }
        return null
    }

}