package com.example.scratchjrcourse.features.units.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.ItemCourseUnitTaskDataBinding
import com.example.scratchjrcourse.databinding.ItemUnitTaskPortionBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.domain.domain.model.DataPortion


class UnitTaskPortionAdapter : RecyclerView.Adapter<UnitTaskPortionAdapter.ViewHolder>() {
    private lateinit var adapter: UnitTaskDataAdapter
    private var portions: MutableList<DataPortion> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnitTaskPortionAdapter.ViewHolder = ViewHolder(
        ItemUnitTaskPortionBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_unit__task_portion, parent, false)
        )
    )

    override fun getItemCount(): Int = portions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(
            dataList = portions[position].data
        )

    fun setData(list: List<DataPortion>) {
        portions.clear()
        portions.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemUnitTaskPortionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataList: List<CourseUnitTaskData>) {
            binding.apply {
                rvPortion.layoutManager = LinearLayoutManager(binding.root.context)
                adapter = UnitTaskDataAdapter()
                adapter.setData(dataList)
                rvPortion.adapter = adapter
            }
        }
    }
}

/**
 * Адаптер для элементов данных [UnitTaskData] с картинками и текстом
 */
class UnitTaskDataAdapter(
) : RecyclerView.Adapter<UnitTaskDataAdapter.ViewHolder>() {

    private var unitTaskData: MutableList<CourseUnitTaskData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCourseUnitTaskDataBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_course_unit_task_data, parent, false)
            )
        )

    override fun getItemCount(): Int = unitTaskData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(unitTaskData[position])

    fun setData(data: List<CourseUnitTaskData>) {
        unitTaskData.clear()
        unitTaskData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCourseUnitTaskDataBinding) :
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
                            ivTaskDataPicTwo.load(unitTaskData.pics[1])
                        }
                    }
                }
                tvTaskDataText.text = unitTaskData.text
            }
        }
    }
}
