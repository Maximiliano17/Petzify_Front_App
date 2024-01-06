package com.maxi.petzify.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.maxi.petzify.MainActivity
import com.maxi.petzify.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportRequestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val btnSinCuenta = findViewById<AppCompatButton>(R.id.btnSinCuenta)

        btnLogin.setOnClickListener{NavigatorHome()}
        btnSinCuenta.setOnClickListener{NavigatorRegister()}
    }
    private fun NavigatorHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun NavigatorRegister(){
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}