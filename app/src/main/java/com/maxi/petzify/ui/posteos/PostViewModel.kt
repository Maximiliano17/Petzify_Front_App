package com.maxi.petzify.ui.posteos

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.petzify.core.ResponseState
import com.maxi.petzify.domain.model.post.PostToSend
import com.maxi.petzify.domain.usecase.post.SendPostUseCase
import com.maxi.petzify.ui.core.Permissions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(private val sendPostUseCase: SendPostUseCase) :
    ViewModel() {
    private var postResponse = MutableStateFlow(PostSendState())
    var _postResponse: StateFlow<PostSendState> = postResponse


    fun uploadPost(post: PostToSend) = viewModelScope.launch(Dispatchers.IO) {
        sendPostUseCase(post).collect{
            when (it) {
                is ResponseState.Success -> {
                    postResponse.value = PostSendState(postResult = it.data)
                }

                is ResponseState.Loading -> {
                    postResponse.value = PostSendState(isLoading = true)
                }
                is ResponseState.Error -> {
                    postResponse.value = PostSendState(error = "Ocurrio un error al subir un post")
                }
            }
        }
    }
    fun convertBitmapToBase64(ivFoto:ImageView):String{
        val bitmap = (ivFoto.drawable as BitmapDrawable).bitmap
        var photoBase64 = ""
        if (bitmap != null){
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            photoBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
        return photoBase64
    }


}