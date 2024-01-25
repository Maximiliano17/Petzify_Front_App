package com.maxi.petzify.domain.usecase

import com.maxi.petzify.domain.LocalRepository
import javax.inject.Inject

class GetLocalDataUseCase @Inject constructor(private val repository: LocalRepository){
    fun getLocalToken() = repository.getLocalToken()
    fun getUsername() = repository.getUsername()
    fun getUserId() = repository.getUserId()
}