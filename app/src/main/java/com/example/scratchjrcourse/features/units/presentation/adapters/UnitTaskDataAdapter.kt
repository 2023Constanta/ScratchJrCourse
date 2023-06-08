package com.example.scratchjrcourse.features.units.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.ItemCourseUnitTaskDataBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData

/**
 * Адаптер для элементов данных [UnitTaskData] с картинками и текстом
 */
class UnitTaskDataAdapter : RecyclerView.Adapter<UnitTaskDataAdapter.ViewHolder>() {

    var unitTaskData: List<CourseUnitTaskData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCourseUnitTaskDataBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_course_unit_task_data, parent, false)
            )
        )

    override fun getItemCount(): Int = unitTaskData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(
        unitTaskData[position]
    )

    class ViewHolder(val binding: ItemCourseUnitTaskDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(unitTaskData: CourseUnitTaskData) {
            binding.apply {
                if (unitTaskData.arePicsVertical == true) {
                    linLayTaskDataContent.orientation = LinearLayout.VERTICAL
                } else {
                    linLayTaskDataContent.orientation = LinearLayout.HORIZONTAL
                }

                if (unitTaskData.pics?.isNotEmpty() == true) {
                    when (unitTaskData.pics.size) {
                        1 -> {
                            ivTaskDataPicOne.load(unitTaskData.pics[0])
                        }

                        2 -> {
                            ivTaskDataPicOne.load(unitTaskData.pics[0])
                            ivTaskDataPicTwo.load(unitTaskData.pics[0])
                        }
                    }
                }
                tvTaskDataText.text = unitTaskData.text
            }
        }
    }
}
