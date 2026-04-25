package com.agamy.focus.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data  class SessionEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val durationMinutes: Int,
    val completedAt: Long = System.currentTimeMillis()
)