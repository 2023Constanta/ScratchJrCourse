package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataPictureEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskEntity
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.domain.domain.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 */
class UnitViewModel(
    private val db: AppDb
) : ViewModel() {
    // Задания блока
    private val _unitTasks = MutableLiveData<List<CourseUnitTask>>()
    val unitTasks get() = _unitTasks

    // Задания курса с картинками
    private val _unitTaskDataWPics = MutableLiveData<List<CourseUnitTaskData>?>()
    val unitTaskDataWPics get() = _unitTaskDataWPics

    // Список индексов
    private var _listOfIndOfMutual = MutableLiveData<List<Int>>()
    val listOfIndOfMutual get() = _listOfIndOfMutual

    // Получение деталей блока
    fun getDetailsOfUnit(unitId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getUnitTaskDao().getTasksByUnitId(unitId).map { it.toDomain() }
                withContext(Dispatchers.Main) {
                    _unitTasks.value = list
                }
            }
        }
    }

    // Получение списка индексом для экрана заданий блока
    fun getListIndOfMutual(unitId: Int, taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val temp = db
                    .getUnitTaskDataDao()
                    .getListOfIndOfMutual(unitId, taskId)

                withContext(Dispatchers.Main) {
                    _listOfIndOfMutual.value = temp
                }
            }
        }
    }

    // Получение данных для задания блока
    fun getPortionOfDataByIdOfMutual(idOfMut: Int, unitId: Int = 0, taskId: Int = 0) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val listOfMutualData =
                    db.getUnitTaskDataDao()
                        .getMutualTasks(idOfMut, taskId, unitId).map { it.toDomain() }

                val portion = mutableListOf<CourseUnitTaskData>()

                for (p in listOfMutualData) {
                    val n = p.copy(
                        pics = db.getPicturesDao().getPicturesByTasksData(p.id)?.map { it.picture }
                    )
                    portion.add(n)
                }

                withContext(Dispatchers.Main) {
                    _unitTaskDataWPics.value = null
                    _unitTaskDataWPics.value = portion
                }

            }
        }
    }

}