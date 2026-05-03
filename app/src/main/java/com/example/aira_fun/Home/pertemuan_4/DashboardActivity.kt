package com.example.aira_fun.Home.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Import binding sesuai nama layout XML kamu
import com.example.aira_fun.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data dari Intent (yang dikirim dari HomeFragment)
        val judul = intent.getStringExtra("judul") ?: "Aira Food Gallery"

        // 3. Set data ke komponen UI
        // Pastikan ID 'txtWelcomeDashboard' ada di file activity_dashboard.xml
        binding.txtWelcomeDashboard.text = "Welcome, Ms. Aira"

        // Pastikan ID 'txtJudulDetail' ada di file activity_dashboard.xml
        binding.txtJudulDetail.text = judul

        // 4. Tombol Back
        binding.btnBack.setOnClickListener {
            finish() // Menutup activity dan kembali ke halaman sebelumnya
        }
    }
}