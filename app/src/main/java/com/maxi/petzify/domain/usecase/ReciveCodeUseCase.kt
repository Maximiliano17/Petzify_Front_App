package com.maxi.petzify.domain.usecase

import com.maxi.petzify.domain.UserRepository
import com.maxi.petzify.domain.model.LoginDataRequired
import javax.inject.Inject

class ReciveCodeUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userData: LoginDataRequired) = repository.reciveCode(userData)
}