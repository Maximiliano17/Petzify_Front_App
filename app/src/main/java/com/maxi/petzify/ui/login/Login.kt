package com.maxi.petzify.ui.login

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
import com.maxi.petzify.domain.model.userdata.UserData
import com.maxi.petzify.domain.usecase.LoginUseCase
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
        binding.btnLogin.setOnClickListener { login() }
        binding.btnSinCuenta.setOnClickListener { NavigatorRegister() }
    }

    private fun login() {
        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                loginUseCase(
                    LoginDataRequired(
                        binding.etUser.text.toString(),
                        null,
                        binding.etPassword.text.toString(),
                        null
                    )
                )
            }
            if (response != null) {
                NavigatorHome(response)
            } else {
                Toast.makeText(this@Login, "Error al consultar la api", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun NavigatorHome(response: UserData) {
        val intent = Intent(this, MainActivity::class.java)

        //intent.putExtra("texto", texto)

        startActivity(intent)
    }

    private fun NavigatorRegister() = startActivity(Intent(this, Register::class.java))

}