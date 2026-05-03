package com.example.aira_fun.Home.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aira_fun.databinding.ActivityRumusBangunRuangBinding
import kotlin.math.PI
import kotlin.math.pow

class RumusBangunRuangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRumusBangunRuangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRumusBangunRuangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judulHalaman = intent.getStringExtra("judul") ?: "Aira Fun Calculator"
        val deskripsiHalaman = intent.getStringExtra("desc") ?: "Hitung Lingkaran & Tabung"

        binding.tvJudul.text = "✨ $judulHalaman ✨"
        binding.tvSubtitle.text = deskripsiHalaman

        binding.btnLingkaran.setOnClickListener {
            val rString = binding.etJariJari.text.toString()
            if (rString.isNotEmpty()) {
                val r = rString.toDouble()
                val luas = PI * r.pow(2)
                binding.tvHasil.text = "%.2f".format(luas)
            } else {
                Toast.makeText(this, "Jari-jarinya diisi dulu ya!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnTabung.setOnClickListener {
            val rString = binding.etJariJari.text.toString()
            val tString = binding.etTinggi.text.toString()
            if (rString.isNotEmpty() && tString.isNotEmpty()) {
                val r = rString.toDouble()
                val t = tString.toDouble()
                val volume = PI * r.pow(2) * t
                binding.tvHasil.text = "%.2f".format(volume)
            } else {
                Toast.makeText(this, "Isi jari-jari dan tinggi dulu!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackMain.setOnClickListener {
            finish()
        }
    }
}