package com.example.aira_fun.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.aira_fun.R

object NotificationHelper {

    private const val CHANNEL_ID = "default_channel"

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        intent: Intent
    ) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "General",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        // ========================================================
        // PERBAIKAN: Buat ID unik untuk mencegah Notifikasi Macet
        // ========================================================
        val uniqueId = kotlin.random.Random.nextInt(1, 100000)

        val pending = PendingIntent.getActivity(
            context,
            uniqueId, // GANTI ANGKA 0 JADI ID UNIK INI BIAR BISA MUNCUL BERKALI-KALI
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pending)
            .build()

        // ID di sini juga menggunakan ID unik yang sama
        manager.notify(uniqueId, notification)
        // ========================================================
    }
}