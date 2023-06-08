package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scratchjrcourse.databinding.FragmentUnitContentBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTasksAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Экран содержания раздела
 */
class UnitContentFragment : Fragment() {

    private val args: UnitContentFragmentArgs by navArgs()
    private lateinit var unitContentAdapter: UnitTasksAdapter
    private lateinit var viewBinding: FragmentUnitContentBinding
    private val unitViewModel: UnitViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentUnitContentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        unitViewModel.getDetailsOfUnit(args.unitId)
        unitViewModel.unitTasks.observe(viewLifecycleOwner) {
            viewBinding.rvCourseContent.layoutManager = LinearLayoutManager(activity)
            // При нажатии на задание блока происходить переход на экран с каруселью данных
            // ТОЛЬКО этого задания
            unitContentAdapter = UnitTasksAdapter(it) { unitId, taskId ->
                findNavController().navigate(
                    UnitContentFragmentDirections.actionUnitContentFragmentToUnitTaskFragment(
                        unitId,
                        taskId
                    )
                )
            }
            viewBinding.rvCourseContent.adapter = unitContentAdapter
        }
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}