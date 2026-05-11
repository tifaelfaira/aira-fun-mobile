package com.example.aira_fun.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityEmailInputBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EmailInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val email = binding.etEmailOnly.text.toString()

            // VALIDASI SOAL a1 (Sudah Benar)
            if (email.isEmpty()) {
                showErrorDialog("Email tidak boleh kosong")
            } else if (!email.endsWith("@gmail.com")) {
                showErrorDialog("Email harus menggunakan domain @gmail.com")
            } else {
                // JIKA VALID: Arahkan ke halaman Registrasi (Soal a1)
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("EXTRA_EMAIL", email)
                startActivity(intent)

                // PERBAIKAN: Tambahkan finish() agar halaman ini hancur/tutup
                // Ini mencegah user balik lagi ke halaman verify setelah regis
                finish()
            }
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Error Validasi")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}