package com.example.aira_fun.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi View Binding
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar beserta tombol back kembali ke Home
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 1. Inisialisasi Adapter TabLayout
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Mengatur nama judul untuk setiap tab sesuai modul
            when (position) {
                0 -> tab.text = "Tab A"
                1 -> tab.text = "Tab B"
                2 -> tab.text = "Produk"
            }
        }.attach()
    }
}