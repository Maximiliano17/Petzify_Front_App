package com.maxi.petzify.domain.usecase

import com.maxi.petzify.domain.ProfileRepository
import com.maxi.petzify.domain.model.LoginDataRequired
import javax.inject.Inject

class ReciveCodeUseCase @Inject constructor(private val repository: ProfileRepository) {
    suspend operator fun invoke(userData: LoginDataRequired) = repository.reciveCode(userData)
}