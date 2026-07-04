package com.example.aira_fun.Agenda

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aira_fun.data.AppDatabase
import com.example.aira_fun.data.entity.AgendaEntity
import com.example.aira_fun.databinding.ActivityAgendaFormBinding
import com.example.aira_fun.utils.PermissionHelper
import com.example.aira_fun.utils.ReminderHelper
import com.example.aira_fun.MainActivity
import kotlinx.coroutines.launch
import java.util.Calendar

class AgendaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaFormBinding
    private lateinit var db: AppDatabase

    // ========================================================
    // PENDAFTARAN LAUNCHER UNTUK PERMISSION NOTIFIKASI
    // ========================================================
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi Kesehatan Diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi Ditolak, Anda mungkin melewatkan jadwal", Toast.LENGTH_SHORT).show()
            }
        }
    // ========================================================

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

        // ========================================================
        // MEMINTA PERMISSION NOTIFIKASI SAAT HALAMAN DIAKSES (ANDROID 13+)
        // ========================================================
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }
        // ========================================================

        binding.btnSaveAgenda.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            // ========================================================
            // TAMBAHAN: MEMBACA INPUTAN MENIT DARI USER
            // ========================================================
            val minutesInput = binding.etMinutes.text.toString()
            // Jika kosong atau bukan angka, otomatis fallback ke default 1 menit
            val minutesToReminder = minutesInput.toIntOrNull() ?: 1
            // ========================================================

            if (title.isNotBlank() && description.isNotBlank()) {

                lifecycleScope.launch {

                    db.agendaDao().insert(
                        AgendaEntity(
                            title = title,
                            description = description
                        )
                    )

                    // ========================================================
                    // PERBAIKAN: Set Kalender agar sinkron dengan ReminderHelper
                    // ========================================================
                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.SECOND, 0) // Nol-kan detik terlebih dahulu

                        // SEKARANG BERUBAH MENJADI DINAMIS SESUAI INPUT USER:
                        add(Calendar.MINUTE, minutesToReminder)
                    }

                    ReminderHelper.setReminder(
                        context = this@AgendaFormActivity,
                        hour = calendar.get(Calendar.HOUR_OF_DAY),
                        minute = calendar.get(Calendar.MINUTE),
                        title = "Pengingat Layanan: $title",
                        message = "Halo warga, jadwal agenda '$description' sudah dekat!",
                        targetActivity = MainActivity::class.java // Diarahkan ke Dashboard Utama FoVerse
                    )
                    // ========================================================

                    Toast.makeText(
                        this@AgendaFormActivity,
                        "Data Kesehatan berhasil disimpan & Reminder diset $minutesToReminder menit!",
                        Toast.LENGTH_LONG
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