package com.rafael.loginappavaliacao.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafael.loginappavaliacao.databinding.ActivityLoginBinding
import com.rafael.loginappavaliacao.ui.home.HomeActivity // Importante para achar a Home

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val validEmail = "aluno@email.com"
    private val validPassword = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {

        binding.editEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validateEmailField()
        }

        binding.editPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validatePasswordField()
        }

        binding.btnLogin.setOnClickListener {
            val isEmailValid = validateEmailField()
            val isPasswordValid = validatePasswordField()

            if (isEmailValid && isPasswordValid) {
                performLogin()
            }
        }
    }

    private fun validateEmailField(): Boolean {
        val email = binding.editEmail.text.toString().trim()
        return if (email.isEmpty() || !email.contains("@")) {
            binding.tilEmail.error = "Insira um e-mail válido"
            false
        } else {
            binding.tilEmail.error = null
            true
        }
    }

    private fun validatePasswordField(): Boolean {
        val password = binding.editPassword.text.toString().trim()
        return if (password.length < 6) {
            binding.tilPassword.error = "A senha deve ter pelo menos 6 caracteres"
            false
        } else {
            binding.tilPassword.error = null
            true
        }
    }

    private fun performLogin() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        if (email == validEmail && password == validPassword) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show()
        }
    }
}