package com.example.aira_fun.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data judul dari Intent
        val judul = intent.getStringExtra("judul") ?: "Aira Food Gallery"

        // FIX: Ganti txtWelcomeUser jadi txtWelcomeDashboard
        binding.txtWelcomeDashboard.text = "Welcome, Ms. Aira"

        // Pastikan ID ini juga ada di XML kamu untuk judulnya
        binding.txtJudulDetail.text = judul

        // Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}