package com.example.aira_fun.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("EMAIL")
        binding.txtEmail.text = email
    }
}