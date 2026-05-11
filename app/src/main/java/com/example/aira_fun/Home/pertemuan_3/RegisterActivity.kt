package com.example.aira_fun.Home.pertemuan_3

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil email dari intent (Soal a1)
        val emailFromLogin = intent.getStringExtra("EXTRA_EMAIL")
        binding.etRegisterEmail.setText(emailFromLogin)
        binding.etRegisterEmail.isEnabled = false // Email tidak boleh diubah (Soal a2)

        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // VALIDASI (Soal a2)
            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show()
            } else if (username.contains(" ")) {
                Toast.makeText(this, "Username tidak boleh mengandung spasi", Toast.LENGTH_SHORT).show()
            } else {
                // SIMPAN KE SHAREDPREFERENCES (Soal a2)
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("saved_username", username)
                editor.putString("saved_password", password)
                editor.apply()

                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke halaman Login
            }
        }
    }
}