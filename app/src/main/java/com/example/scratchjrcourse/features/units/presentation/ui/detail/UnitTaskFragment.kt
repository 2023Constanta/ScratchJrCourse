package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.scratchjrcourse.databinding.FragmentUnitTaskBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskCarouselAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * Экран-карусель. Показывает экраны, на которых показываются порции данных
 */
class UnitTaskFragment : Fragment() {

    private val args: UnitTaskFragmentArgs by navArgs()
    private lateinit var binding: FragmentUnitTaskBinding
    private val unitViewModel: UnitViewModel by sharedViewModel()

    private lateinit var adapter: UnitTaskCarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnitTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onCreate: $unitViewModel")

        Log.d(TAG, "onViewCreated: ${args.unitId}, ${args.taskId}")

//        val fragmentsList = List<Fragment>(1) { UnitPortionFragment() }
        unitViewModel.getCountOfScreens(args.unitId)
        var fragmentsList = mutableListOf<Fragment>()
        unitViewModel.count.observe(viewLifecycleOwner) {
            fragmentsList = MutableList<Fragment>(it) { UnitPortionFragment() }
//        }

            Log.d(TAG, "onViewCreated: ${fragmentsList.size}")

            adapter = UnitTaskCarouselAdapter(
                fragments = fragmentsList,
                requireActivity()
            )

            binding.wpCourseUnitTasks.adapter = adapter
        }
    }

        companion object {
            private val TAG = this::class.java.simpleName
        }



}