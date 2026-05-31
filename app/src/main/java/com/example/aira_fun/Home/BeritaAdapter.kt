package com.example.aira_fun.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aira_fun.data.model.CatFactModel
import com.example.aira_fun.databinding.ItemBeritaBinding

class BeritaAdapter(private val listBerita: List<CatFactModel>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(val binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val data = listBerita[position]

        // FIX: Sekarang Judul diambil langsung dari deskripsi yang kita selipkan di HomeFragment!
        // Jika data formatnya dipisah menggunakan tanda "|", kita pecah teksnya.
        if (data.fact.contains("|")) {
            val pecahTeks = data.fact.split("|")
            holder.binding.tvJudulBerita.text = pecahTeks[0].trim()
            holder.binding.tvIsiBerita.text = "${pecahTeks[1].trim()} Warga diimbau untuk selalu menjaga kesehatan."
        } else {
            // Jaga-jaga kalau ada data yang tidak pakai tanda "|"
            holder.binding.tvJudulBerita.text = "Info Kesehatan Desa"
            holder.binding.tvIsiBerita.text = "${data.fact} Warga diimbau untuk selalu menjaga kesehatan."
        }
    }

    override fun getItemCount(): Int = listBerita.size
}