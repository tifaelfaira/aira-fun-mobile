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

        // 1. Setup View Binding
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("LifeCycle", "ProfileActivity: onCreate")

        // 2. Menerima data Intent (dengan nilai default agar tidak kosong)
        val judul = intent.getStringExtra("judul") ?: "Profil Pengguna"
        val desc = intent.getStringExtra("desc") ?: "Halaman detail profil Aira Food"

        // 3. Set data ke komponen UI
        // Pastikan ID ini sama persis dengan yang ada di activity_profile.xml
        binding.txtJudulDetail.text = judul
        binding.txtDescDetail.text = desc

        // 4. Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}