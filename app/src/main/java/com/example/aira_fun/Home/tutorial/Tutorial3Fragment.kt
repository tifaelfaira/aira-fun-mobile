package com.example.aira_fun.Home.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    // Mengaktifkan ViewBinding agar bisa membaca tombol btnAyoMulai di dalam layout
    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // AKSI KLIK TOMBOL "AYO MULAI" YANG ADA DI TENGAH FRAGMENT
        binding.btnAyoMulai.setOnClickListener {
            // Simpan status di SharedPreferences supaya tutorial/onboarding tidak muncul lagi setelah login
            val sharedPref = requireActivity().getSharedPreferences("tutorial_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isFinished", true).apply()

            // Pindah halaman langsung ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}