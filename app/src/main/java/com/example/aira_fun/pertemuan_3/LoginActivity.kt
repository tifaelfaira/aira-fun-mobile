package com.example.aira_fun.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

            // LOGIKA BARU: Username harus sama dengan Password
            if (email == password && email.isNotEmpty()) {

                // SIMPAN STATUS LOGIN (Tambahan Pertemuan 6)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", email)
                editor.apply()

                // KODINGAN ASLI KAMU (Tetap dipertahankan)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_EMAIL", email)
                startActivity(intent)
                finish()

            } else {
                // Tampilkan pesan error sesuai modul jika tidak sama
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Silahkan coba lagi (Username & Password harus sama)")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}