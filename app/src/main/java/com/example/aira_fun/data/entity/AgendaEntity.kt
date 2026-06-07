package com.example.aira_fun.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agendas")
data class AgendaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String
)