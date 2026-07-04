package com.example.aira_fun.Home.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Import binding sesuai nama layout XML kamu
import com.example.aira_fun.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("judul") ?: "Aira Food Gallery"
        binding.txtWelcomeDashboard.text = "Welcome, Ms. Aira"
        binding.txtJudulDetail.text = judul

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}