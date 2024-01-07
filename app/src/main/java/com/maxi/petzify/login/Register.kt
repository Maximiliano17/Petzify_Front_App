package com.maxi.petzify.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.maxi.petzify.Api.Models.Login.LoginResponse
import com.maxi.petzify.Api.Models.Register.RegisterResponse
import com.maxi.petzify.Api.Models.User
import com.maxi.petzify.Api.RetrofitClient
import com.maxi.petzify.MainActivity
import com.maxi.petzify.R
import com.maxi.petzify.databinding.ActivityLoginBinding
import com.maxi.petzify.databinding.ActivityRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
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
        screenSplash.setKeepOnScreenCondition{false}

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener{ register() }
        binding.btnTengoCuenta.setOnClickListener{ NavigatorLogin() }
    }
    private fun register(){
        lifecycleScope.launch {
            val response: Response<RegisterResponse>? = withContext(Dispatchers.IO){
                RetrofitClient.consumirApi.register(User(binding.etUser.text.toString(), binding.etMail.text.toString(), binding.etPassword.text.toString(), "institution"))
            }

            if(response != null && response.isSuccessful) NavigatorHome()
            else Toast.makeText(this@Register, "Error al consultar la api", Toast.LENGTH_SHORT).show()
        }
    }
    private fun NavigatorHome() = startActivity(Intent(this, MainActivity::class.java))

    private fun NavigatorLogin() = startActivity(Intent(this, Login::class.java))

}