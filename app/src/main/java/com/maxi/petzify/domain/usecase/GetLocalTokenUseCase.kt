package com.maxi.petzify.domain.usecase

import com.maxi.petzify.domain.LocalRepository
import javax.inject.Inject

class GetLocalTokenUseCase @Inject constructor(private val repository: LocalRepository){
    operator fun invoke() = repository.getLocalToken()
}
