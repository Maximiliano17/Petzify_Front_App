package com.maxi.petzify.Views.posteos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.maxi.petzify.Views.core.viewModels.UserDataViewModel
import com.maxi.petzify.databinding.FragmentPosteosBinding
import com.maxi.petzify.domain.model.post.PostToSend
import com.maxi.petzify.domain.usecase.GetLocalDataUseCase
import com.maxi.petzify.ui.core.VerifyEditText
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class Posteos : Fragment() {
    private val userDataViewModel by viewModels<UserDataViewModel>()
    private val postViewModel by viewModels<PostViewModel>()
    private var _binding: FragmentPosteosBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var getLocalDataUseCase: GetLocalDataUseCase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPosteosBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        observePerfilDetail()
    }

    private fun observePerfilDetail() {
        lifecycleScope.launch(Dispatchers.IO) {
            userDataViewModel._userData.collectLatest { value ->
                withContext(Dispatchers.Main) {
                    if (value.isLoading) {
                        //TODO activar loadings o bones
                        Log.i("yo", "esta cargando")
                    } else if (value.error.isNotBlank()) {
                        //TODO mostrar algun diseño de falla de carga
                        Toast.makeText(
                            requireContext(), value.error, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        //mostrar datos
                        value.userData?.let { user ->
                            binding.tvNameUser.text = user.username
                            Picasso.get().load(user.profile).into(binding.ivPerfil)
                        }
                        initListeners()
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnPost.setOnClickListener { verifyPost() }
    }

    private fun verifyPost() {
        val allHaveText = VerifyEditText.haveContent(
            listOf(
                binding.etTitulo,
                binding.etDescripccion
            )
        )
        if (allHaveText) {
            postViewModel.uploadPost(
                PostToSend(
                    getLocalDataUseCase.getUsername(),
                    getLocalDataUseCase.getUserId(),
                    binding.etTitulo.text.toString(),
                    binding.etDescripccion.text.toString(),
                    ""
                )
            )
            observeUploadingPost()
        }
    }

    private fun observeUploadingPost() {

        lifecycleScope.launch(Dispatchers.IO) {
            postViewModel._postResponse.collectLatest { value ->
                withContext(Dispatchers.Main) {
                    if (value.isLoading) {
                        //TODO activar loadings o bones
                        Log.i("yo", "esta cargando")

                    } else if (value.error.isNotBlank()) {
                        //TODO mostrar algun diseño de falla de carga
                        Toast.makeText(
                            requireContext(), value.error, Toast.LENGTH_SHORT
                        ).show()
                        Log.i("fragment posteos",value.error)

                    } else {
                        //mostrar datos
                        value.postResult?.let { message ->
                            //mostrar un mensaje de exito
                            Log.i("fragment posteos", message.toString())
                        }
                    }
                }
            }
        }
    }

}