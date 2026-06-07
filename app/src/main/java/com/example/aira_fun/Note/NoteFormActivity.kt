package com.example.aira_fun.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aira_fun.data.AppDatabase
import com.example.aira_fun.data.entity.NoteEntity
import com.example.aira_fun.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
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

        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {

                lifecycleScope.launch {

                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )

                    db.noteDao().insert(note)

                    Toast.makeText(
                        this@NoteFormActivity,
                        "Note berhasil disimpan",
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