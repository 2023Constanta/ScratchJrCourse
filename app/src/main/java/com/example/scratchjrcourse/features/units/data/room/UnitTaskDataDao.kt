package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UnitTaskDataDao {
    @Query("SELECT * FROM unit_task_data WHERE unitId = :unitId AND taskId = :taskId")
    suspend fun getTasksByUnitId(unitId: Int, taskId: Int): List<UnitTaskDataEntity>
}