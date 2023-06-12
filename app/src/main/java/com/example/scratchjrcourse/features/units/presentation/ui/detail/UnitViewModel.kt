package com.example.scratchjrcourse.features.units.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask
import com.example.scratchjrcourse.features.units.domain.domain.model.DataPortion
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
    val unitTasks: LiveData<List<CourseUnitTask>> get() = _unitTasks

    private val _dataPortions = MutableLiveData<List<DataPortion>>()
    val dataPortions: LiveData<List<DataPortion>> get() = _dataPortions

    fun getPortionsOfData(unitId: Int, taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // 1. Идет получение индексов общности. Для каждого экранчика только его данные
                val listOfIds = db
                    .getUnitTaskDataDao()
                    .getListOfIndOfMutual(unitId, taskId)

                val dataPortions = mutableListOf<DataPortion>()
                // 2. Проходится по списку индексов
                for (i in listOfIds) {

                    val portionByIdOfMutual = db.getUnitTaskDataDao()
                        .getMutualTasks(i, taskId, unitId)
                        .map {
                            it.toDomain()
                        }
                        .map { data ->
                            data.copy(
                                pics = db.getPicturesDao().getPicturesByTasksData(data.id)
                                    ?.map { it.picture }
                            )
                        }

                    dataPortions.add(DataPortion(portionByIdOfMutual))
                }

                withContext(Dispatchers.Main) {
                    this@UnitViewModel._dataPortions.value = dataPortions
                }

            }
        }
    }

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

    companion object {
        private const val TAG = "UnitViewModel"
    }

}