package com.example.scratchjrcourse.features.units.presentation.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.FragmentUnitsListBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnitsListFragment : Fragment() {

    private lateinit var adapter: UnitsListAdapter
    private val viewModel by viewModel<UnitsListViewModel>()

    private lateinit var viewBinding: FragmentUnitsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentUnitsListBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rvTasks.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter = UnitsListAdapter { id: Int ->
            onItemClick(id)
        }
        viewModel.getUnits()

        viewModel.units.observe(viewLifecycleOwner) {
            adapter.units = it
            Log.d("UnitsFrag", "onViewCreated: ${it}")
            viewBinding.rvTasks.adapter = adapter
        }
    }

    private fun onItemClick(id: Int) {
        findNavController().navigate(UnitsListFragmentDirections.actionUnitsListFragmentToUnitContentFragment(id))
    }
}