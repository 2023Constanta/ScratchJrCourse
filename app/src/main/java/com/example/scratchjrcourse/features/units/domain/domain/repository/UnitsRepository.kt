package com.example.scratchjrcourse.features.units.domain.domain.repository

import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnit

interface UnitsRepository {
    suspend fun getUnits(): List<CourseUnit>
}