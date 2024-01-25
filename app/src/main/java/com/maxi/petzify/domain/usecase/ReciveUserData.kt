package com.maxi.petzify.domain.usecase

import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.ProfileRepository
import com.maxi.petzify.domain.model.userData.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReciveUserData @Inject constructor(private val repository: ProfileRepository) {
    suspend operator fun invoke(): Flow<ResponseState<UserData>> = flow {

        emit(ResponseState.Loading<UserData>())

        val data = repository.getUserData()

        if (data != null) {
            emit(ResponseState.Success<UserData>(data))
        } else {
            emit(ResponseState.Error<UserData>("Revisar los datos."))
        }

    }
}