package com.maxi.petzify.Views.perfil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.maxi.petzify.databinding.FragmentPerfilBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class Perfil : Fragment() {

    private val perfilViewModel by viewModels<PerfilViewModel>()
    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(layoutInflater, container, false)
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
            perfilViewModel._userData.collectLatest { value ->
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
                            binding.nombreUser.text = user.username
                            binding.tvSeguidoresNum.text = user.followers.size.toString()
                            binding.tvSeguidosNum.text = user.following.size.toString()
                            Picasso.get().load(user.profile).into(binding.ivPerfil)
                            Picasso.get().load(user.banner).into(binding.ivBanner)
                        }
                    }
                }
            }
        }
    }
}