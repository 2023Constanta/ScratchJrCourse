package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskEntity

@Dao
interface UnitTaskDao {
    @Query("SELECT * FROM unit_tasks WHERE unit_id = :unitId")
    suspend fun getTasksByUnitId(unitId: Int): List<UnitTaskEntity>
}