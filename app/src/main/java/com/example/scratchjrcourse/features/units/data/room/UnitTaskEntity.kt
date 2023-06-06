package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_tasks")
data class UnitTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val unitId: Int,
    val name: String
) {
}