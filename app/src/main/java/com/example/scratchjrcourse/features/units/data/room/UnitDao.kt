package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UnitDao {
    @Query("SELECT * FROM units")
    suspend fun getUnits(): List<UnitEntity>
}