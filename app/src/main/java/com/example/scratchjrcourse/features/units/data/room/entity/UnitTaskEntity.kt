package com.example.scratchjrcourse.features.units.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_tasks")
data class UnitTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "unit_id")
    val unitId: Int,
    val name: String,
    @ColumnInfo(name = "is_quest")
    val isQuestion: Boolean
)