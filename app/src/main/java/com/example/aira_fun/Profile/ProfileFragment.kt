package com.example.aira_fun.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aira_fun.databinding.FragmentProfileBinding // Sesuaikan dengan nama XML baru kamu

class ProfileFragment : Fragment() {

    // Deklarasi View Binding (Menggunakan FragmentProfileBinding)
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi layout binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- SETUP TOOLBAR ---
        // Mengambil kontrol toolbar dari activity induk
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Developer Profile"

        // Tempat naruh logic klik atau set data profil lainnya nanti
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Penting: Hapus binding untuk mencegah memory leak
        _binding = null
    }
}