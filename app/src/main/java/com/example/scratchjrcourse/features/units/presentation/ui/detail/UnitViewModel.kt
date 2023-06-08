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
import com.example.scratchjrcourse.features.units.domain.domain.model.Picture
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

    // Количество экранов
    private val _countOfScreens = MutableLiveData<Int>()
    val countOfScreens get() = _countOfScreens

    // Задания курса с картинками
    private val _unitTaskDataWPics = MutableLiveData<List<CourseUnitTaskData>>()
    val unitTaskDataWPics get() = _unitTaskDataWPics

    // Минимальный индекс
    private var _minIndexOfMutual = MutableLiveData<Int>()
    val minIndexOfMutual get() = _minIndexOfMutual

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

    // Получение минимального индекса для заданий блока
    fun getMinIndexOfMutual(unitId: Int, taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val temp = db
                    .getUnitTaskDataDao()
                    .getMinIndexOfMutualBy(unitId, taskId)

                Log.d("UnitViewModel", "getMinIndexOfMutual: $temp")

                withContext(Dispatchers.Main) {
                    _minIndexOfMutual.value = temp
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
                    _unitTaskDataWPics.value = portion
                }

            }
        }
    }

    // Получение кол-ва экранов для карусели
    fun getCountOfScreens(unitId: Int, taskId: Int) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val count = db.getUnitTaskDataDao().getCountOfMutualTasks(unitId, taskId)
                withContext(Dispatchers.Main) {
                    _countOfScreens.value = count
                }
            }
        }
}

fun UnitTaskEntity.toDomain(): CourseUnitTask =
    CourseUnitTask(id, unitId, name)

fun UnitTaskDataEntity.toDomain(): CourseUnitTaskData =
    CourseUnitTaskData(
        id,
        unitId,
        taskId,
        text,
        idOfMutual = idOfMutual,
        pics = listOf(),
        arePicsVertical = arePicsVert
    )

fun UnitTaskDataPictureEntity.toDomain(): Picture = Picture(
    id, unitId, taskId, taskDataId, picture
)
