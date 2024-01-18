package com.maxi.petzify.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.maxi.petzify.MainActivity
import com.maxi.petzify.R
import com.maxi.petzify.databinding.ActivityRegisterBinding
import com.maxi.petzify.domain.model.LoginDataRequired
import com.maxi.petzify.domain.model.registerResponse.RegisterResult
import com.maxi.petzify.domain.usecase.RegisterUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint//Agregar siempre esto!!!!
class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    @Inject
    lateinit var registerUseCase: RegisterUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportRequestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_register)

        //Iniciar pantalla de carga
        screenSplash.setKeepOnScreenCondition { false }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener { register() }
        binding.btnTengoCuenta.setOnClickListener { NavigatorLogin() }
    }

    private fun register() {
        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                registerUseCase(
                    LoginDataRequired(
                        binding.etUser.text.toString(),
                        binding.etMail.text.toString(),
                        binding.etPassword.text.toString(),
                        "institution"
                    )
                )
            }
            if (response != null) {
                NavigatorHome(response)
            } else {
                Toast.makeText(this@Register, "Error al consultar la api", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun NavigatorHome(response: RegisterResult) =
        startActivity(Intent(this, MainActivity::class.java))

    private fun NavigatorLogin() = startActivity(Intent(this, Login::class.java))

}