package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_task_data")
data class UnitTaskDataEntity(
    @PrimaryKey
    val id: Int,
    val unitId: Int,
    val taskId: Int,
    val text: String?,
//    val pictures: List<Int>?
)
