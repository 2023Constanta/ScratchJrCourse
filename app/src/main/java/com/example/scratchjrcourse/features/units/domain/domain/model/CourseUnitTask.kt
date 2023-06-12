package com.example.scratchjrcourse.features.units.domain.domain.model

/**
 * Задание раздела [CourseUnit] курса
 */
data class CourseUnitTask(
    val id: Int,
    val unitId: Int,
    val name: String,
    val isQuestion: Boolean
)