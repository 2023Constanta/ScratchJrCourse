package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.FragmentUnitPortionBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskDataAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Экран-элемент карусели. Показывает порцию данных, получает их по id mutual
 */
class UnitPortionFragment : Fragment() {

    private val adapter = UnitTaskDataAdapter()
    private lateinit var binding: FragmentUnitPortionBinding
    private val unitViewModel: UnitViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnitPortionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Получение unitId AND taskId
        val unitId = arguments?.getInt("unitId")
        val taskId = arguments?.getInt("taskId")
        val mutualId = arguments?.getInt("mutualId")

        // Логи
        Log.d(TAG, "onViewCreated: unitId = $unitId")
        Log.d(TAG, "onViewCreated: taskId = $taskId")
        Log.d(TAG, "onViewCreated: mutualId = $mutualId")

        // 2. Получение индекст мумуал по unitId AND taskId

        unitViewModel.getPortionOfDataByIdOfMutual(
            idOfMut = mutualId!!,
            unitId = unitId!!,
            taskId = taskId!!
        )

        binding.rvPortion.layoutManager = LinearLayoutManager(activity)
        unitViewModel.unitTaskDataWPics.observe(requireActivity()) {
            if (it != null) {
                adapter.unitTaskData = it
            }
        }
        binding.rvPortion.adapter = adapter
    }

    companion object {
        private val TAG = "UnitPortionFragment"
    }

}