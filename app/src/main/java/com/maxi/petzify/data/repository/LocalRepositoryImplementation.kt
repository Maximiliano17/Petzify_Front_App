package com.maxi.petzify.data.repository

import android.util.Log
import com.maxi.petzify.Petzify.Companion.pref
import com.maxi.petzify.data.core.interceptor.TokenManager
import com.maxi.petzify.domain.LocalRepository
import javax.inject.Inject

class LocalRepositoryImplementation @Inject constructor(private val tokenManager: TokenManager) :
    LocalRepository {
    override fun getLocalToken(): String? {
        runCatching { tokenManager.getToken() }
            .onSuccess { return it }
            .onFailure {
                Log.i(
                    "Error",
                    "Ha ocurrido un error al consultar el almacenamiento local."
                )
            }
        return null
    }

    override fun getUsername(): String? {
        runCatching {
            pref.getSring("username")
        }.onSuccess {
            return it
        }.onFailure {
            Log.i("Error", "No se encontro un usuario.")
        }
        return null
    }

    override fun getUserId(): String? {
        runCatching {
            pref.getSring("userId")
        }.onSuccess {
            return it
        }.onFailure {
            Log.i("Error", "No se encontro un id.")
        }
        return null
    }

}