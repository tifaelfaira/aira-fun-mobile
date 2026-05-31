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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aira_fun.Home.pertemuan_2.RumusBangunRuangActivity
import com.example.aira_fun.Home.pertemuan_3.LoginActivity
import com.example.aira_fun.Home.pertemuan_4.DashboardActivity
import com.example.aira_fun.Home.pertemuan_4.ProfileActivity
import com.example.aira_fun.Home.pertemuan_6.WebViewActivity
import com.example.aira_fun.Home.pertemuan_10.TenthActivity
import com.example.aira_fun.data.model.CatFactModel // Mengambil model dari folder data.model yang tersisa
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

        // Chip Logic - Aman
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            chip?.let {
                Toast.makeText(requireContext(), "Layanan: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi Tombol - Semuanya UTUH
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

        // AKSI TOMBOL PERTEMUAN 10
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
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

        // AKSI TOMBOL REFRESH HIJAU
        binding.btnRefreshBerita.setOnClickListener {
            Toast.makeText(requireContext(), "Berita diperbarui!", Toast.LENGTH_SHORT).show()
            fetchPublicApiBerita()
        }

        // Jalankan fungsi pemicu list berita
        fetchPublicApiBerita()
    }

    // Fungsi fetch berita yang sudah dijinakkan tanpa perlu file BeritaNetwork
    private fun fetchPublicApiBerita() {
        binding.rvBeritaDesa.layoutManager = LinearLayoutManager(requireContext())
        loadCadanganBeritaBinaDesa()
    }

    // === PERUBAHAN DI SINI: MENGGABUNGKAN JUDUL + ISI AGAR KEDUANYA IKUT TERACAK ===
    private fun loadCadanganBeritaBinaDesa() {
        // Format pengiriman data: "Judul Berita | Isi Deskripsi Berita"
        val kumpulanBeritaDesa = listOf(
            CatFactModel(fact = "Penyuluhan Gizi & Stunting | Kegiatan edukasi di balai desa mendapatkan antusiasme tinggi dari para ibu dan balita.", length = 100),
            CatFactModel(fact = "Jadwal Posyandu Lansia | Layanan cek kesehatan rutin Desa Sukamaju resmi dibuka kembali setiap hari Sabtu minggu pertama.", length = 100),
            CatFactModel(fact = "Sosialisasi Air Bersih | Warga diimbau untuk selalu merebus air hingga mendidih sebelum dikonsumsi demi menjaga kesehatan pencernaan.", length = 100),
            CatFactModel(fact = "Layanan Cek Darah Gratis | Poli Umum Bina Desa membuka pemeriksaan tekanan darah dan kolesterol gratis khusus hari Jumat ini.", length = 100),
            CatFactModel(fact = "Info Stok Apotek Desa | Apotek Desa Sukamaju menerima pasokan obat-obatan baru, ketersediaan vitamin untuk warga kini aman.", length = 100),
            CatFactModel(fact = "Agenda Senam Sehat | Kader kesehatan desa mengadakan senam jantung sehat bersama di lapangan utama besok pagi pukul 06.00 WIB.", length = 100),
            CatFactModel(fact = "Tips Cegah Demam Berdarah | Waspada musim pancaroba, Puskesmas pembantu mengimbau warga melakukan gerakan 3M Plus secara berkala.", length = 100),
            CatFactModel(fact = "Pemeriksaan Gigi Anak | Pemeriksaan kesehatan gigi gratis akan diselenggarakan di SDN 01 Sukamaju oleh tim dokter spesialis.", length = 100)
        )

        // Mengocok seluruh list berita secara acak kemudian mengambil 3 item teratas
        val dataAcakPilihan = kumpulanBeritaDesa.shuffled().take(3)

        // Set data hasil acikan maut langsung ke BeritaAdapter buatanmu
        binding.rvBeritaDesa.adapter = BeritaAdapter(dataAcakPilihan)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}