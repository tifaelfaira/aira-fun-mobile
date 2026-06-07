package com.example.aira_fun.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aira_fun.data.dao.AgendaDao
import com.example.aira_fun.data.dao.NoteDao
import com.example.aira_fun.data.entity.AgendaEntity
import com.example.aira_fun.data.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        AgendaEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun agendaDao(): AgendaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}