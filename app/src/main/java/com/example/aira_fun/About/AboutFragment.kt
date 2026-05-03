package com.example.aira_fun.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
// SESUAIKAN DI SINI: Jika nama file XML-mu fragment_about.xml, maka pakai FragmentAboutBinding
import com.example.aira_fun.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    // Sesuaikan tipe datanya di sini juga
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Sesuaikan class inflate-nya
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity
        // Pastikan di dalam fragment_about.xml ada view dengan id: toolbar
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "About Bina Desa"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}