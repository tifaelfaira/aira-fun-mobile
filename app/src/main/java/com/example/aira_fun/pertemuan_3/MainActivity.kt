package com.example.aira_fun.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
// IMPORT ini sangat penting agar MainActivity kenal file di luar folder pertemuan_3
import com.example.aira_fun.RumusBangunRuangActivity
import com.example.aira_fun.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "MainActivity: onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil email dari login, defaultnya Ms. Aira (Syarat Laporan)
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "Ms. Aira"
        binding.txtWelcome.text = "Welcome FoVerse"

        // 1. Tombol Rumus (Pindah ke RumusBangunRuangActivity)
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            intent.putExtra("judul", "Kalkulator Bangun Ruang")
            intent.putExtra("desc", "Hitung Volume & Luas Permukaan")
            startActivity(intent)
        }

        // 2. Tombol Custom 1 (Profile)
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("judul", "User Profile")
            intent.putExtra("desc", "Halaman informasi akun anda")
            startActivity(intent)
        }

        // 3. Tombol Custom 2 (Dashboard Food)
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("judul", "FoodieVerse Gallery")
            intent.putExtra("desc", "Daftar menu makanan spesial")
            startActivity(intent)
        }

        // 4. Tombol Logout (Konfirmasi + Snackbar sesuai syarat tugas)
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val intentLogout = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogout)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    // Munculkan SnackBar sesuai instruksi Pak Fikri
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}