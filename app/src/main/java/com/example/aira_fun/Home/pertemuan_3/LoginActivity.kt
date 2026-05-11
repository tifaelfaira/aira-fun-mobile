package com.example.aira_fun.Home.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.BaseActivity
import com.example.aira_fun.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Tombol registrasi tetap ada untuk syarat Quiz
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, EmailInputActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val inputUser = binding.etEmail.text.toString()
            val inputPass = binding.etPassword.text.toString()

            // Ambil data dari SharedPreferences (jika pernah regis)
            val savedUser = sharedPref.getString("saved_username", null)
            val savedPass = sharedPref.getString("saved_password", null)

            // --- LOGIKA AGAR BISA LANGSUNG MASUK ---

            // 1. Logika Login Bebas: Cukup isi Username dan Password dengan kata yang sama
            val isBypassLogin = (inputUser == inputPass && inputUser.isNotEmpty())

            // 2. Logika Sesuai Registrasi: (Syarat Quiz a3)
            val isSharedPrefLogin = (inputUser == savedUser && inputPass == savedPass && inputUser.isNotEmpty())

            // Jika salah satu benar, langsung masuk ke Dashboard
            if (isBypassLogin || isSharedPrefLogin) {

                // Simpan session
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", inputUser)
                editor.apply()

                // Pindah ke Dashboard
                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("USER_EMAIL", inputUser)
                startActivity(intent)
                finish()

            } else {
                // Tampilkan error hanya jika input kosong atau tidak cocok keduanya
                MaterialAlertDialogBuilder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Username atau Password salah!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}