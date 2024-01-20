package com.maxi.petzify.domain.usecase

import com.maxi.petzify.domain.UserRepository
import javax.inject.Inject

class ReciveUserData @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke() = repository.getUserData()
}