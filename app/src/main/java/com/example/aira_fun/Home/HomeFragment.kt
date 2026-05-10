package com.example.aira_fun.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aira_fun.Home.pertemuan_2.RumusBangunRuangActivity
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.Home.pertemuan_4.DashboardActivity
import com.example.aira_fun.Home.pertemuan_4.ProfileActivity
import com.example.aira_fun.Home.pertemuan_6.WebViewActivity
import com.example.aira_fun.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        // Toolbar Setup
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)

        // Chip Logic - Cara paling aman biar nggak error di library
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            chip?.let {
                Toast.makeText(requireContext(), "Layanan: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi Tombol
        binding.btnRumus.setOnClickListener {
            startActivity(Intent(requireContext(), RumusBangunRuangActivity::class.java))
        }

        binding.btnCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.btnCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Logout
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