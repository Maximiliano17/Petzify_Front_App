package com.maxi.petzify.ui.posteos

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.maxi.petzify.databinding.FragmentPosteosBinding
import com.maxi.petzify.domain.model.post.PostToSend
import com.maxi.petzify.domain.usecase.GetLocalDataUseCase
import com.maxi.petzify.ui.core.Permissions
import com.maxi.petzify.ui.core.extensions.haveContent
import com.maxi.petzify.ui.core.viewModels.UserDataViewModel
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

    //encardado de abrir la camara y guardar la foto en un imageView
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val extras = result.data?.extras
                val imgBitmap = extras!!["data"] as Bitmap?
                binding.ivTakedPhotos.setImageBitmap(imgBitmap)
            }

        }


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
        initObservers()
        observeUploadingPost()
    }

    private fun initObservers() {
        observePerfilDetail()
    }


    //observar el perfil del usuario y mostrar sus datos
    private fun observePerfilDetail() {
        lifecycleScope.launch(Dispatchers.IO) {
            userDataViewModel._userData.collectLatest { value ->
                withContext(Dispatchers.Main) {
                    if (value.isLoading) {
                        Log.i("yo", "esta cargando")
                    } else if (value.error.isNotBlank()) {
                        Toast.makeText(
                            requireContext(), value.error, Toast.LENGTH_SHORT
                        ).show()
                    } else {
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
        binding.btnCaptureImage.setOnClickListener { openCameraProcess() }
    }

    //proceso de crear intent para abrir la camara
    private fun openCameraProcess() {
        val isPermission = Permissions().checkCameraPermiso(requireActivity())
        if (isPermission) {
            openCamera()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            cameraLauncher.launch(intent)
        }
    }

    //verificar que el post tenga los datos correctos
    private fun verifyPost() {
        val allHaveText = listOf(binding.etTitulo, binding.etDescripccion).haveContent()
        if (allHaveText) {
            //convertir la imagen del imageview a base64
            val base64 = postViewModel.convertBitmapToBase64(binding.ivTakedPhotos)
            postViewModel.uploadPost(
                PostToSend(
                    getLocalDataUseCase.getUsername(),
                    getLocalDataUseCase.getUserId(),
                    binding.etTitulo.text.toString(),
                    binding.etDescripccion.text.toString(),
                    base64
                )
            )
        }
    }

    //observar el proceso de subida del post
    private fun observeUploadingPost() {
        lifecycleScope.launch(Dispatchers.IO) {
            postViewModel._postResponse.collectLatest { value ->
                withContext(Dispatchers.Main) {
                    if (value.isLoading) {
                        Log.i("yo", "esta cargando")

                    } else if (value.error.isNotBlank()) {
                        Toast.makeText(
                            requireContext(), value.error, Toast.LENGTH_SHORT
                        ).show()
                        Log.i("fragment posteos", value.error)

                    } else if(!value.postResult.isNullOrEmpty()) {
                        binding.etDescripccion.text.clear()
                        binding.etTitulo.text.clear()
                        findNavController().navigate(
                            PosteosDirections.actionNavigatonPosteosToNavigationHome()
                        )
                    }
                }
            }
        }
    }

}