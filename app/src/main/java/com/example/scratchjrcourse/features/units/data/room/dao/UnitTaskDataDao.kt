package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity

@Dao
interface UnitTaskDataDao {
    @Query("SELECT * FROM unit_task_data WHERE unit_id = :unitId AND task_id = :taskId")
    suspend fun getTasksByUnitId(unitId: Int, taskId: Int): List<UnitTaskDataEntity>

    @Query("SELECT * FROM unit_task_data")
    suspend fun getTasks(): List<UnitTaskDataEntity>
}