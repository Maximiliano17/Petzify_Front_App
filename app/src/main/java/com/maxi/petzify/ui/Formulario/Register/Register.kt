package com.maxi.petzify.ui.Formulario.Register

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
import com.maxi.petzify.domain.model.code.Code
import com.maxi.petzify.domain.usecase.ReciveCodeUseCase
import com.maxi.petzify.domain.usecase.RegisterUseCase
import com.maxi.petzify.ui.core.editTextVerify.VerifyEditText
import com.maxi.petzify.ui.Formulario.Login.Login
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

    @Inject
    lateinit var reciveCodeUseCase: ReciveCodeUseCase

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
        binding.btnRegister.setOnClickListener { verifyEditTextUserData() }
        binding.btnTengoCuenta.setOnClickListener { NavigatorLogin() }
    }

    //Verificar que todos los campos esten llenos
    private fun verifyEditTextUserData() {

        val allHaveText = VerifyEditText.haveContent(
            listOf(
                binding.etUser,
                binding.etMail,
                binding.etPassword
            )
        )

        if (allHaveText) {
            registerProcess(
                LoginDataRequired(
                    binding.etUser.text.toString(),
                    binding.etMail.text.toString(),
                    binding.etPassword.text.toString(),
                    "institution"
                )
            )
        } else {
            Toast.makeText(this@Register, "Error, completar los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerProcess(userData: LoginDataRequired) {
        lifecycleScope.launch {
            val code = reciveCode(userData)

            if (checkCode(code)) {
                register(userData)
            } else {
                Toast.makeText(this@Register, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun reciveCode(userData: LoginDataRequired): Code? {
        return withContext(Dispatchers.IO) {
            reciveCodeUseCase(userData)
        }
    }

    private fun checkCode(code: Code?): Boolean {
        //realizar comprobacion
        return true
    }

    private suspend fun register(userData: LoginDataRequired) {
        val registerResult = withContext(Dispatchers.IO) {
            registerUseCase(userData)
        }
        if (registerResult != null) {
            //cosas
        } else {
            //cosas
        }
    }


    private fun NavigatorHome(response: Code) =
        startActivity(Intent(this, MainActivity::class.java))

    private fun NavigatorLogin() = startActivity(Intent(this, Login::class.java))

}