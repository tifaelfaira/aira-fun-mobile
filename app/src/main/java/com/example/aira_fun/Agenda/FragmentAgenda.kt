package com.example.aira_fun.Agenda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aira_fun.data.AppDatabase
import com.example.aira_fun.data.entity.AgendaEntity
import com.example.aira_fun.databinding.FragmentAgendaBinding
import kotlinx.coroutines.launch

class FragmentAgenda : Fragment() {

    private var _binding: FragmentAgendaBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: AgendaAdapter

    private val agendas = mutableListOf<AgendaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgendaBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        adapter = AgendaAdapter(
            agendas,
            this
        )

        binding.rvAgenda.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvAgenda.adapter = adapter

        val divider = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )

        binding.rvAgenda.addItemDecoration(divider)

        binding.fabAddFavorite.setOnClickListener {
            val intent = Intent(requireActivity(), AgendaFormActivity::class.java)
            startActivity(intent)
        }

        fetchAgendas()
    }

    private fun fetchAgendas() {
        lifecycleScope.launch {
            val data = db.agendaDao().getAll()
            agendas.clear()
            agendas.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteAgenda(
        agenda: AgendaEntity
    ) {
        lifecycleScope.launch {
            db.agendaDao().delete(agenda)
            fetchAgendas()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAgendas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}