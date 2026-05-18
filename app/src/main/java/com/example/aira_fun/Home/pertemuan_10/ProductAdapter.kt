package com.example.aira_fun.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.aira_fun.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            tvProductName.text = item.name
            tvProductPrice.text = item.price

            // PENGATURAN GLIDE SUPAYA GAMBAR DIPAKSA MUNCUL DAN DI-CACHE
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Simpan gambar di memori biar tidak putih lagi
                .placeholder(android.R.drawable.ic_menu_gallery) // Gambar sementara saat loading
                .error(android.R.drawable.stat_notify_error) // Jika gagal download
                .into(imgProduct)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}