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
import com.maxi.petzify.Api.Models.User
import com.maxi.petzify.Api.RetrofitClient
import com.maxi.petzify.MainActivity
import com.maxi.petzify.R
import com.maxi.petzify.databinding.ActivityLoginBinding
import com.maxi.petzify.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
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
    private fun initListeners(){
        binding.btnLogin.setOnClickListener{login()}
        binding.btnSinCuenta.setOnClickListener{NavigatorRegister()}
    }
    private fun login(){
        lifecycleScope.launch {
            val response: Response<LoginResponse>? = withContext(Dispatchers.IO){
                RetrofitClient.consumirApi.login(User(binding.etUser.text.toString(), null, binding.etPassword.text.toString(), null))
            }
            if(response != null && response.isSuccessful) NavigatorHome()
            else Toast.makeText(this@Login, "Error al consultar la api", Toast.LENGTH_SHORT).show()
        }
    }
    private fun NavigatorHome() = startActivity(Intent(this, MainActivity::class.java))
    private fun NavigatorRegister() = startActivity(Intent(this, Register::class.java))

}