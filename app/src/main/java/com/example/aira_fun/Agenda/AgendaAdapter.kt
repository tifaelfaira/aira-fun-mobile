package com.example.aira_fun.Agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aira_fun.data.entity.AgendaEntity
import com.example.aira_fun.databinding.ItemAgendaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AgendaAdapter(
    // PERBAIKAN: Nama list diubah jadi agendas agar sinkron dengan fragment
    private val agendas: List<AgendaEntity>,
    private val fragment: FragmentAgenda
) : RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    inner class AgendaViewHolder(
        val binding: ItemAgendaBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AgendaViewHolder {

        val binding = ItemAgendaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AgendaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AgendaViewHolder,
        position: Int
    ) {
        val agenda = agendas[position]

        holder.binding.tvTitle.text = agenda.title
        holder.binding.tvDescription.text = agenda.description

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Agenda")
                .setMessage("Yakin ingin menghapus data ini?")
                .setPositiveButton("Ya") { dialog, _ ->

                    fragment.deleteAgenda(agenda)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return agendas.size
    }
}