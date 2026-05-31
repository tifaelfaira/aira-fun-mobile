package com.example.aira_fun.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memasang adapter fragment baru
        binding.viewPagerOnboarding.adapter = OnboardingAdapter(this)
        binding.dotIndicatorOnboarding.attachTo(binding.viewPagerOnboarding)

        binding.viewPagerOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // SESUAI REQUEST: Tombol bawah dipaksa tetap GONE di semua slide agar tidak double
                if (position == 2) {
                    binding.btnAyoMulai.visibility = View.GONE // Diubah ke GONE agar tombol bawah tidak muncul lagi
                } else {
                    binding.btnAyoMulai.visibility = View.GONE
                }
            }
        })

        // Tetap dipertahankan utuh tanpa dihapus agar tidak memicu eror kodingan
        binding.btnAyoMulai.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}