package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitEntity

@Dao
interface UnitDao {
    @Query("SELECT * FROM units")
    suspend fun getUnits(): List<UnitEntity>
}