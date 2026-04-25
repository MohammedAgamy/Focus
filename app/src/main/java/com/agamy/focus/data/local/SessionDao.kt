package com.agamy.focus.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: SessionEntity)

    @Query("SELECT COUNT(*) FROM session WHERE type = 'focus'")
    fun getFocusCount(): Flow<Int>
}