package com.agamy.focus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SessionEntity::class], version = 1, exportSchema = false)
abstract class PomodoroDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

    companion object {
        @Volatile
        private var INSTANCE: PomodoroDatabase? = null
        fun get(context: Context) = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context, PomodoroDatabase::class.java, "pomodoro_db")
                .build().also { INSTANCE = it }
        }
    }
}