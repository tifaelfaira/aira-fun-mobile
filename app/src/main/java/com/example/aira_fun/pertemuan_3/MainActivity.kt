package com.example.aira_fun.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
// IMPORT ini tetap dipertahankan agar tidak merah
import com.example.aira_fun.RumusBangunRuangActivity
import com.example.aira_fun.databinding.ActivityMainBinding
import com.example.aira_fun.pertemuan_6.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "MainActivity: onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- 1. TAMBAHAN PERTEMUAN 6: Setup Toolbar Bina Desa ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Bina Desa - Health"

        // Ambil email dari login (Asli dari saya)
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "Ms. Aira"
        binding.txtWelcome.text = "Welcome FoVerse"

        // --- 2. TAMBAHAN PERTEMUAN 6: Tombol WebView (Website Alwaysdata) ---
        binding.btnWebView.setOnClickListener {
            val intentWeb = Intent(this, WebViewActivity::class.java)
            startActivity(intentWeb)
        }

        // 1. Tombol Rumus (ASLI KAMU - TIDAK DIHAPUS)
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            intent.putExtra("judul", "Kalkulator Bangun Ruang")
            intent.putExtra("desc", "Hitung Volume & Luas Permukaan")
            startActivity(intent)
        }

        // 2. Tombol Custom 1 (Profile) - ASLI KAMU
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("judul", "User Profile")
            intent.putExtra("desc", "Halaman informasi akun anda")
            startActivity(intent)
        }

        // 3. Tombol Custom 2 (Dashboard Food) - ASLI KAMU
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("judul", "FoodieVerse Gallery")
            intent.putExtra("desc", "Daftar menu makanan spesial")
            startActivity(intent)
        }

        // 4. Tombol Logout (Update: Tambahkan Clear SharedPreferences)
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // --- 3. TAMBAHAN PERTEMUAN 6: Hapus Sesi Login ---
                    val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    val intentLogout = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogout)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    // SnackBar instruksi Pak Fikri (ASLI KAMU)
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}