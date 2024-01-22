package com.maxi.petzify.ui.Formulario.Login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.maxi.petzify.MainActivity
import com.maxi.petzify.R
import com.maxi.petzify.databinding.ActivityLoginBinding
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.usecase.GetLocalTokenUseCase
import com.maxi.petzify.domain.usecase.LoginUseCase
import com.maxi.petzify.ui.Formulario.Register.Register
import com.maxi.petzify.ui.core.VerifyEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var loginUseCase: LoginUseCase


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportRequestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener { verifyEditTextUserData() }
        binding.btnSinCuenta.setOnClickListener { NavigatorRegister() }
    }

    private fun verifyEditTextUserData() {
        val allHaveText = VerifyEditText.haveContent(
            listOf(
                binding.etUser,
                binding.etPassword
            )
        )
        if (allHaveText) {
            login(
                LoginDataRequired(
                    binding.etUser.text.toString(),
                    null,
                    binding.etPassword.text.toString(),
                    null
                )
            )
        } else {
            Toast.makeText(this@Login, "Error, completar los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun login(userData: LoginDataRequired) {
        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) { loginUseCase(userData) }
            if (response != null) {
                NavigatorHome(response.token)
            } else {
                Toast.makeText(this@Login, "Error al consultar la api", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun NavigatorHome(token: String) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun NavigatorRegister() = startActivity(Intent(this, Register::class.java))

}