package com.example.aira_fun.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
// PASTIKAN BARIS INI ADA DAN BENAR:
import com.example.aira_fun.data.entity.AgendaEntity

@Dao
interface AgendaDao {

    @Query("SELECT * FROM agendas")
    suspend fun getAll(): List<AgendaEntity>

    @Insert
    suspend fun insert(agenda: AgendaEntity)

    @Delete
    suspend fun delete(agenda: AgendaEntity)
}