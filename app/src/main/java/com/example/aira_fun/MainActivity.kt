package com.example.aira_fun // <--- SUDAH DISESUAIKAN

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.PI
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Menyambungkan komponen desain (XML) ke kode (Kotlin)
        val etJariJari = findViewById<EditText>(R.id.etJariJari)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnLingkaran = findViewById<Button>(R.id.btnLingkaran)
        val btnTabung = findViewById<Button>(R.id.btnTabung)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // 2. Memberikan perintah pada tombol Luas Lingkaran
        btnLingkaran.setOnClickListener {
            val rString = etJariJari.text.toString()

            if (rString.isNotEmpty()) {
                val r = rString.toDouble()
                val luas = PI * r.pow(2)

                tvHasil.text = "Luas Lingkaran: %.2f".format(luas)

                // Logcat
                Log.d("AiraFunLog", "Berhasil hitung lingkaran! Jari-jari: $r, Hasil: $luas")
            } else {
                Toast.makeText(this, "Jari-jarinya diisi dulu ya!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Memberikan perintah pada tombol Volume Tabung
        btnTabung.setOnClickListener {
            val rString = etJariJari.text.toString()
            val tString = etTinggi.text.toString()

            if (rString.isNotEmpty() && tString.isNotEmpty()) {
                val r = rString.toDouble()
                val t = tString.toDouble()
                val volume = PI * r.pow(2) * t

                tvHasil.text = "Volume Tabung: %.2f".format(volume)

                // Logcat
                Log.d("AiraFunLog", "Berhasil hitung tabung! r: $r, t: $t, Hasil: $volume")
            } else {
                Toast.makeText(this, "Jari-jari dan Tingginya harus diisi dua-duanya!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}