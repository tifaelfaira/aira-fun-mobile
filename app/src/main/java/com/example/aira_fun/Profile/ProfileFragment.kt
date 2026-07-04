package com.example.aira_fun.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter // Tambahkan import ini
import android.widget.Toast        // Tambahkan import ini untuk test klik
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aira_fun.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        // --- TAMBAHAN UNTUK ARRAYADAPTER (LISTVIEW) ---

        val menuSettings = listOf("Privacy Policy", "About Project", "Terms of Service", "Help Center")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            menuSettings
        )

        // 3. Pasang adapter ke ListView
        binding.lvProfileMenu.adapter = adapter

        binding.lvProfileMenu.setOnItemClickListener { _, _, position, _ ->
            val selectedMenu = menuSettings[position]
            Toast.makeText(requireContext(), "Membuka: $selectedMenu", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}