package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UnitTaskDao {
    @Query("SELECT * FROM unit_tasks WHERE unitId = :unitId")
    suspend fun getTasksByUnitId(unitId: Int): List<UnitTaskEntity>
}