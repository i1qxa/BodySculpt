package com.interaps.BodySculpt.ui.menu_of_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentMenuOfDayBinding
import com.interaps.BodySculpt.databinding.FragmentRecipeListBinding
import com.interaps.BodySculpt.ui.menu_of_day.rv.MenuOfDayRVAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Calendar

class MenuOfDayFragment : Fragment() {

    private val rvAdapter by lazy { MenuOfDayRVAdapter() }

    private val viewModel by viewModels<MenuOfDayViewModel>()

    private var _binding: FragmentMenuOfDayBinding? = null
    private val binding: FragmentMenuOfDayBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuOfDayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupFabClickListeners()
        setupRecyclerView()
    }

    private fun setupFabClickListeners() {
        binding.fabAddMenuItem.setOnClickListener {
            findNavController().navigate(R.id.action_menuOfDayFragment_to_recipeListFragment)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.menuOfDay.collect() { listMenuItems ->
                if (listMenuItems != null && listMenuItems.isNotEmpty()) {
                    binding.tvEmptyMenuDayList.visibility = View.GONE
                    binding.tvTotalInfoHeader.visibility = View.VISIBLE
                    binding.tvCarboHeader.visibility = View.VISIBLE
                    binding.tvFatHeader.visibility = View.VISIBLE
                    binding.tvKcalHeader.visibility = View.VISIBLE
                    binding.tvProteinsHeader.visibility = View.VISIBLE
                    rvAdapter.submitList(listMenuItems)
                }
            }
        }
        viewModel.commonInfo.observe(viewLifecycleOwner){
            binding.tvKcalHeader.text = requireContext().getString(R.string.kcal_header, it.caloriesTotal.toString())
            binding.tvProteinsHeader.text = requireContext().getString(R.string.proteins_header, it.proteinTotal.toString())
            binding.tvFatHeader.text = requireContext().getString(R.string.fat_header, it.fatTotal.toString())
            binding.tvCarboHeader.text = requireContext().getString(R.string.carbo_header, it.carboTotal.toString())
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvMenuOfDay) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}