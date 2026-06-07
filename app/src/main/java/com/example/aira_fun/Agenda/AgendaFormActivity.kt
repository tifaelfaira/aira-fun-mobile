package com.example.aira_fun.Agenda

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aira_fun.data.AppDatabase
import com.example.aira_fun.data.entity.AgendaEntity
import com.example.aira_fun.databinding.ActivityAgendaFormBinding
import kotlinx.coroutines.launch

class AgendaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgendaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // ========================================================
        // KODE TAMBAHAN BIAR TOMBOL KEMBALI DI TOOLBAR BISA DIKLIK
        // ========================================================
        setSupportActionBar(binding.toolbarForm)
        binding.toolbarForm.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        // ========================================================

        binding.btnSaveAgenda.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {

                lifecycleScope.launch {

                    db.agendaDao().insert(
                        AgendaEntity(
                            title = title,
                            description = description
                        )
                    )

                    Toast.makeText(
                        this@AgendaFormActivity,
                        "Data Kesehatan berhasil disimpan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }

            } else {

                Toast.makeText(
                    this,
                    "Isi semua kolom",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}