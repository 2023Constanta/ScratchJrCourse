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

        val fragId = arguments?.getInt("frag_id")
        Log.d(TAG, "onViewCreated: $fragId")

        if (fragId != null) {
            unitViewModel.getPortionOfDataByIdOfMutual(fragId.plus(1))
//            unitViewModel.getPortionOfDataByIdOfMutual(15)

            binding.rvPortion.layoutManager = LinearLayoutManager(activity)

//            binding.rvPortion.visibility = View.GONE
//
//            binding.clQuestion.visibility = View.VISIBLE

            unitViewModel.unitTaskDataWPics.observe(requireActivity()) {
//                with(binding.clQuestion) {
//                    val ad = it.first()
//                    binding.ivQuestionPic.load(R.drawable.pic2)
//                    binding.tvQuestionText.text = ad.text
//                    binding.
                adapter.unitTaskData = it
                }
//                Log.d(TAG, "onViewCreated: $it")
            }

            binding.rvPortion.adapter = adapter

        }
//    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}