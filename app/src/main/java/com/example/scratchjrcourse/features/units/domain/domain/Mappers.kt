package com.example.scratchjrcourse.features.units.domain.domain

import com.example.scratchjrcourse.features.units.data.room.entity.UnitEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskEntity
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnit
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData

fun UnitTaskEntity.toDomain(): CourseUnitTask =
    CourseUnitTask(id, unitId, name, isQuestion)

fun UnitTaskDataEntity.toDomain(): CourseUnitTaskData =
    CourseUnitTaskData(
        id,
        unitId,
        taskId,
        text,
        idOfMutual = idOfMutual,
        pics = listOf(),
        arePicsVertical = arePicsVert,
        isQuestion = isQuestion,
        answerText = answerText
    )

fun UnitEntity.toDomain(): CourseUnit =
    CourseUnit(id, name, description)
