package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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

        Log.d(TAG, "args: ${args.unitId}, ${args.taskId}")

        // 1. Получ. кол-ва экранов
        unitViewModel.getCountOfScreens(args.unitId, args.taskId)
        unitViewModel.getMinIndexOfMutual(args.unitId, args.taskId)

        var minInd = 0

        // Идет получение минимального индекса
        unitViewModel.minIndexOfMutual.observe(viewLifecycleOwner) {
            minInd = it
        }

        // 2. Создаются экраны
        unitViewModel.countOfScreens.observe(viewLifecycleOwner) {
            var fragmentsList =  mutableListOf<Fragment>()
            fragmentsList = MutableList(it) {
                val fragment = UnitPortionFragment()
                fragment.arguments = bundleOf(
                    "unitId" to args.unitId,
                    "taskId" to args.taskId,
                    "mutualId" to it+minInd
                )
                fragment
            }
            Log.d(TAG, "size of list of fragments: ${fragmentsList.size}")
            adapter = UnitTaskCarouselAdapter(
                fragments = fragmentsList,
                requireActivity()
            )
            // 3. Прикрепляются
            binding.wpCourseUnitTasks.adapter = adapter
        }


        binding.btnNextUnit.setOnClickListener {
            var currPage = binding.wpCourseUnitTasks.currentItem
            var countOfPages =
                (binding.wpCourseUnitTasks.adapter as UnitTaskCarouselAdapter).itemCount

            var nextPage = currPage + 1
            if (nextPage >= countOfPages) {
                nextPage = 0
            }
            binding.wpCourseUnitTasks.setCurrentItem(nextPage, true)
        }

        binding.btnPrevUnit.setOnClickListener {
            var currPage = binding.wpCourseUnitTasks.currentItem
            var countOfPages =
                (binding.wpCourseUnitTasks.adapter as UnitTaskCarouselAdapter).itemCount

            var prevPage = currPage - 1
            if (prevPage < 0) {
                prevPage = countOfPages - 1
            }
            binding.wpCourseUnitTasks.setCurrentItem(prevPage, true)
        }
    }

    companion object {
        private val TAG = "UnitTaskFragment"
    }


}