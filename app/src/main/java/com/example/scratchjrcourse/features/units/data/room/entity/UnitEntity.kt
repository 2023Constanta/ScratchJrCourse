package com.example.scratchjrcourse.features.units.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс раздела курса
 */
@Entity(tableName = "units")
data class UnitEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String?
)
