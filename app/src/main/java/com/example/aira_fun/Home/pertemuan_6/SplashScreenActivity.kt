package com.example.aira_fun.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aira_fun.R
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pastikan layout activity_splash_screen.xml sudah ada di folder res/layout
        setContentView(R.layout.activity_splash_screen)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)

            if (isLogin) {
                // Ke halaman utama (BaseActivity) - UTUH KODE ASLIMU
                startActivity(Intent(this@SplashScreenActivity, BaseActivity::class.java))
            } else {
                // === INI SAJA YANG DIUBAH: Diarahkan ke Onboarding dulu sebelum ke Login ===
                // Menggunakan path lengkap agar import LoginActivity di atas tidak abu-abu
                startActivity(Intent(this@SplashScreenActivity, com.example.aira_fun.onboarding.OnboardingActivity::class.java))
            }
            finish()
        }
    }
}