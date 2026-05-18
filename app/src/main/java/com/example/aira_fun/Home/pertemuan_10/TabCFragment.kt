package com.example.aira_fun.Home.pertemuan_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aira_fun.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // 50 DATA MEDIS - VARIASI GAMBAR BANYAK & POSISI LINK SAMA DIACAK SEJAUH MUNGKIN
    private val productList = listOf(
        // --- BARIS 1 - 5 (Variasi Gambar Berbeda Semua Secara Berurutan) ---
        ProductModel("Termometer Digital", "Rp 35.000", "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=250&q=60"), // Gbr A
        ProductModel("Alat Tensi Darah Digital", "Rp 340.000", "https://images.unsplash.com/photo-1584515979956-d9f6e5d09982?w=250&q=60"), // Gbr B
        ProductModel("Stetoskop Medis Klinis", "Rp 125.000", "https://images.unsplash.com/photo-1603398938378-e54eab446dde?w=250&q=60"), // Gbr C
        ProductModel("Alat Cek Gula Darah 3-in-1", "Rp 195.000", "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=250&q=60"), // Gbr D
        ProductModel("Pulse Oximeter Fingertip", "Rp 65.000", "https://images.unsplash.com/photo-1550572017-edd951b55104?w=250&q=60"), // Gbr E
        ProductModel("Timbangan Badan Digital", "Rp 85.000", "https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=250&q=60"), // Gbr F
        ProductModel("Alat Cek Asam Urat", "Rp 145.000", "https://images.unsplash.com/photo-1611241893603-3c359704e0ee?w=250&q=60"), // Gbr G
        ProductModel("Alat Cek Kolesterol", "Rp 160.000", "https://images.unsplash.com/photo-1551076805-e1869033e561?w=250&q=60"), // Gbr H
        ProductModel("Thermogun Infrared", "Rp 110.000", "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=250&q=60"), // Gbr I
        ProductModel("Palu Refleks Saraf Medis", "Rp 40.000", "https://images.unsplash.com/photo-1584515934148-9b88950f6128?w=250&q=60"), // Gbr J

        // --- BARIS 6 - 10 (Variasi Gambar Baru Lagi, Mengurangi Duplikasi) ---
        ProductModel("Kotak P3K Dinding", "Rp 95.000", "https://images.unsplash.com/photo-1579684389782-64d84b5e901a?w=250&q=60"), // Gbr K
        ProductModel("Masker Medis 3-Ply (Box)", "Rp 25.000", "https://images.unsplash.com/photo-1579152604600-416aa27f8bc5?w=250&q=60"), // Gbr L
        ProductModel("Hand Sanitizer Gel 500ml", "Rp 35.000", "https://images.unsplash.com/photo-1532187863486-abf9d39d6618?w=250&q=60"), // Gbr M
        ProductModel("Cairan Antiseptik Luka", "Rp 18.000", "https://images.unsplash.com/photo-1512290923902-8a9f81dc236c?w=250&q=60"), // Gbr N
        ProductModel("Plester Wound Luka Steril", "Rp 10.000", "https://images.unsplash.com/photo-1542884748-2b87b36c6b90?w=250&q=60"), // Gbr O
        ProductModel("Kasa Steril Gulung", "Rp 15.000", "https://images.unsplash.com/photo-1559757175-5700dde675bc?w=250&q=60"), // Gbr P
        ProductModel("Obat Batuk Sirup Herbal", "Rp 24.000", "https://images.unsplash.com/photo-1584017911766-d451b3d0e843?w=250&q=60"), // Gbr Q
        ProductModel("Minyak Kayu Putih Alami", "Rp 32.000", "https://images.unsplash.com/photo-1584483777113-475107aea633?w=250&q=60"), // Gbr R
        ProductModel("Kain Mitela Pembalut P3K", "Rp 12.000", "https://images.unsplash.com/photo-1597481499750-3e6b22637e12?w=250&q=60"), // Gbr S
        ProductModel("Gel Kompres Demam Anak", "Rp 15.000", "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=250&q=60"), // Gbr T

        // --- BARIS 11 - 20 (Mulai Pengulangan Tapi Disilang Sangat Jauh dari Atas) ---
        ProductModel("Kapsul Vitamin C 1000mg", "Rp 50.000", "https://images.unsplash.com/photo-1550572017-edd951b55104?w=250&q=60"), // Ulang Gbr E
        ProductModel("Minyak Ikan Omega 3", "Rp 110.000", "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=250&q=60"), // Ulang Gbr A
        ProductModel("Multivitamin Kompleks", "Rp 65.000", "https://images.unsplash.com/photo-1611241893603-3c359704e0ee?w=250&q=60"), // Ulang Gbr G
        ProductModel("Madu Murni Bina Desa", "Rp 85.000", "https://images.unsplash.com/photo-1584515979956-d9f6e5d09982?w=250&q=60"), // Ulang Gbr B
        ProductModel("Suplemen Kalsium Sendi", "Rp 95.000", "https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=250&q=60"), // Ulang Gbr F
        ProductModel("Vitamin D3 Sistem Imun", "Rp 75.000", "https://images.unsplash.com/photo-1603398938378-e54eab446dde?w=250&q=60"), // Ulang Gbr C
        ProductModel("Teh Hijau Herbal Alami", "Rp 35.000", "https://images.unsplash.com/photo-1551076805-e1869033e561?w=250&q=60"), // Ulang Gbr H
        ProductModel("Kapsul Ekstrak Temulawak", "Rp 45.000", "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=250&q=60"), // Ulang Gbr D
        ProductModel("Suplemen Zat Besi Anemia", "Rp 30.000", "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=250&q=60"), // Ulang Gbr I
        ProductModel("Kapsul Daun Kelor Herbal", "Rp 40.000", "https://images.unsplash.com/photo-1584515934148-9b88950f6128?w=250&q=60"), // Ulang Gbr J

        // --- BARIS 21 - 30 (Siklus Pengulangan Acak Baru) ---
        ProductModel("Gedung Klinik Bina Desa", "Info Layanan", "https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=250&q=60"), // Ulang Gbr F
        ProductModel("Mobil Ambulans Siaga", "Emergency", "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=250&q=60"), // Ulang Gbr D
        ProductModel("Ruang Inap Steril Desa", "Rp 200.000", "https://images.unsplash.com/photo-1551076805-e1869033e561?w=250&q=60"), // Ulang Gbr H
        ProductModel("Laboratorium Medis", "Cek Darah", "https://images.unsplash.com/photo-1579684389782-64d84b5e901a?w=250&q=60"), // Ulang Gbr K
        ProductModel("Apotek Obat Lengkap", "24 Jam", "https://images.unsplash.com/photo-1579152604600-416aa27f8bc5?w=250&q=60"), // Ulang Gbr L
        ProductModel("Masker N95 Steril Medis", "Rp 15.000", "https://images.unsplash.com/photo-1532187863486-abf9d39d6618?w=250&q=60"), // Ulang Gbr M
        ProductModel("Sarung Tangan Nitrile Box", "Rp 60.000", "https://images.unsplash.com/photo-1512290923902-8a9f81dc236c?w=250&q=60"), // Ulang Gbr N
        ProductModel("Cairan Infus NaCl 500ml", "Rp 25.000", "https://images.unsplash.com/photo-1542884748-2b87b36c6b90?w=250&q=60"), // Ulang Gbr O
        ProductModel("Spuit Jarum Suntik 3cc", "Rp 5.000", "https://images.unsplash.com/photo-1559757175-5700dde675bc?w=250&q=60"), // Ulang Gbr P
        ProductModel("Baju Hazmat APD Lengkap", "Rp 85.000", "https://images.unsplash.com/photo-1584017911766-d451b3d0e843?w=250&q=60"), // Ulang Gbr Q

        // --- BARIS 31 - 40 (Siklus Terakhir Pengacakan Jarak Jauh) ---
        ProductModel("Susu Low Fat Nutrisi", "Rp 90.000", "https://images.unsplash.com/photo-1584483777113-475107aea633?w=250&q=60"), // Ulang Gbr R
        ProductModel("Gandum Oat Diet Sehat", "Rp 45.000", "https://images.unsplash.com/photo-1597481499750-3e6b22637e12?w=250&q=60"), // Ulang Gbr S
        ProductModel("Gula Jagung Rendah Kalori", "Rp 38.000", "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=250&q=60"), // Ulang Gbr T
        ProductModel("Garam Rendah Natrium", "Rp 25.000", "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=250&q=60"), // Ulang Gbr A
        ProductModel("Minyak Zaitun Organik", "Rp 135.000", "https://images.unsplash.com/photo-1584515979956-d9f6e5d09982?w=250&q=60"), // Ulang Gbr B
        ProductModel("Teh Chamomile Relaksasi", "Rp 42.000", "https://images.unsplash.com/photo-1603398938378-e54eab446dde?w=250&q=60"), // Ulang Gbr C
        ProductModel("Kapsul Jahe Merah Hangat", "Rp 48.000", "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=250&q=60"), // Ulang Gbr D
        ProductModel("Sari Kurma Suplemen", "Rp 55.000", "https://images.unsplash.com/photo-1550572017-edd951b55104?w=250&q=60"), // Ulang Gbr E
        ProductModel("Balsem Pereda Nyeri Otot", "Rp 20.000", "https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=250&q=60"), // Ulang Gbr F
        ProductModel("Sabun Antiseptik Cuci", "Rp 20.000", "https://images.unsplash.com/photo-1611241893603-3c359704e0ee?w=250&q=60")  // Ulang Gbr G
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}