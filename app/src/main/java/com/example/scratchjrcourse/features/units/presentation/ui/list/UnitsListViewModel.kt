package com.example.scratchjrcourse.features.units.presentation.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.data.room.entity.UnitEntity
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UnitsListViewModel(
    private val db: AppDb
) : ViewModel() {
    private val _units = MutableLiveData<List<CourseUnit>>()
    val units get() = _units

    fun getUnits() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getUnitDao().getUnits().map { it.toDomain() }
                withContext(Dispatchers.Main) {
                    _units.value = list
                }
            }
        }
    }

}

fun UnitEntity.toDomain(): CourseUnit =
    CourseUnit(id, name, description)