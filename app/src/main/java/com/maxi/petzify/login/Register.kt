package com.maxi.petzify.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.maxi.petzify.R

class Register : AppCompatActivity() {
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

        val btnTengoCuenta = findViewById<AppCompatButton>(R.id.btnTengoCuenta)

        btnTengoCuenta.setOnClickListener{NavigatorLogin()}
    }
    private fun NavigatorLogin(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}