package com.example.aira_fun.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
// Import binding sesuai nama layout XML kamu
import com.example.aira_fun.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("LifeCycle", "ProfileActivity: onCreate")

        val judul = intent.getStringExtra("judul") ?: "Profil Pengguna"
        val desc = intent.getStringExtra("desc") ?: "Halaman detail profil Aira Food"

        binding.txtJudulDetail.text = judul
        binding.txtDescDetail.text = desc

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}