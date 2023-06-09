package com.example.scratchjrcourse.features.units.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.LayoutItemDefaultBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnit

/**
 * Адаптер доступных блоков
 */
class UnitsListAdapter(
    private val onItemClicked: (id: Int) -> Unit
) : RecyclerView.Adapter<UnitsListAdapter.ViewHolder>() {

    private var list: MutableList<CourseUnit> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutItemDefaultBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_default, parent, false)
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            with(list[position]) {
                tvName.text = name

                tvName.setOnClickListener {
                    onItemClicked.invoke(list[position].id)
                }
            }
        }
    }

    fun setUnits(units: List<CourseUnit>) {
        list.clear()
        list.addAll(units)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: LayoutItemDefaultBinding) : RecyclerView.ViewHolder(binding.root)
}