package com.example.aira_fun.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "LoginActivity: onCreate")
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                // Mengirim email agar bisa tampil "Welcome, Ms. Aira"
                intent.putExtra("USER_EMAIL", email)
                startActivity(intent)
                finish()
            }
        }
    }
}