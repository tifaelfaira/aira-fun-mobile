package com.example.aira_fun.pertemuan_3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("LifeCycle", "ProfileActivity: onCreate")

        // Menerima data Intent dari MainActivity
        val judul = intent.getStringExtra("judul")
        val desc = intent.getStringExtra("desc")

        binding.txtJudulDetail.text = judul
        binding.txtDescDetail.text = desc

        binding.btnBack.setOnClickListener { finish() }
    }
}