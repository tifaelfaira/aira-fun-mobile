package com.example.aira_fun.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// IMPORT SESUAI STRUKTUR FOLDER (Pertemuan di dalam folder Home)
import com.example.aira_fun.Home.pertemuan_2.RumusBangunRuangActivity
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.Home.pertemuan_4.DashboardActivity
import com.example.aira_fun.Home.pertemuan_4.ProfileActivity
import com.example.aira_fun.Home.pertemuan_6.WebViewActivity

import com.example.aira_fun.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Bina Desa - Health"

        binding.txtWelcome.text = "Welcome FoVerse"

        // Tombol-tombol Intent
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireContext(), RumusBangunRuangActivity::class.java)
            intent.putExtra("judul", "Kalkulator")
            startActivity(intent)
        }

        binding.btnCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.btnCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}