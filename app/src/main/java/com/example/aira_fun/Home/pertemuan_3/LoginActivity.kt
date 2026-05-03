package com.example.aira_fun.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
// PENTING: Tambahkan import ini agar LoginActivity kenal sama BaseActivity yang ada di luar
import com.example.aira_fun.BaseActivity
import com.example.aira_fun.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "LoginActivity: onCreate")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // LOGIKA: Username harus sama dengan Password
            if (email == password && email.isNotEmpty()) {

                // SIMPAN STATUS LOGIN
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", email)
                editor.apply()

                // PINDAH KE BASEACTIVITY (Dashboard Utama)
                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("USER_EMAIL", email)
                startActivity(intent)
                finish()

            } else {
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Silahkan coba lagi (Username & Password harus sama)")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}