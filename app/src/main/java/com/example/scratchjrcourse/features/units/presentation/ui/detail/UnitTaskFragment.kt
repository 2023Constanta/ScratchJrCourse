package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scratchjrcourse.databinding.FragmentUnitTaskBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskDataAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class UnitTaskFragment : Fragment() {

    private val args: UnitTaskFragmentArgs by navArgs()
    private lateinit var binding: FragmentUnitTaskBinding
    private val unitViewModel: UnitViewModel by sharedViewModel()
    private lateinit var adapter: UnitTaskDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUnitTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onCreate: $unitViewModel")

        Log.d(TAG, "onViewCreated: ${args.unitId}, ${args.taskId}")

        unitViewModel.getDataForTasks(args.unitId, args.taskId)

        unitViewModel.unitTaskData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: ${it.size}")

            adapter = UnitTaskDataAdapter()
            binding.wpCourseUnitTasks.layoutManager = LinearLayoutManager(activity)
            adapter.unitTaskData = it
            binding.wpCourseUnitTasks.adapter = adapter
        }
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }


}