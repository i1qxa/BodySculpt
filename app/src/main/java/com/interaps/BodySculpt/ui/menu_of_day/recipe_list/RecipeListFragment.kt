package com.interaps.BodySculpt.ui.menu_of_day.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentRecipeListBinding
import com.interaps.BodySculpt.ui.menu_of_day.recipe_list.rv.RecipesRVAdapter

class RecipeListFragment : Fragment() {

    private val rvAdapter by lazy { RecipesRVAdapter() }

    private val viewModel by viewModels<RecipeListViewModel>()

    private var _binding: FragmentRecipeListBinding? = null
    private val binding: FragmentRecipeListBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnSearchClickListener()
        setupRecyclerView()
        observeViewModel()
        setupRvAdapter()
    }

    private fun setupRvAdapter(){
        rvAdapter.onBtnAddClickListener = {
            viewModel.addRecipeToMenu(it)
            Toast.makeText(requireContext(), "${it.label} successfully added to the menu of the day", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel(){
        viewModel.listRecipes.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)
        }
        viewModel.errorRequest.observe(viewLifecycleOwner){
            if (it){
                Toast.makeText(requireContext(), requireContext().getText(R.string.error_loading_data), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvRecipeList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupBtnSearchClickListener(){
        binding.btnSearch.setOnClickListener {
            viewModel.getRecipeList(binding.etRecipeName.text.toString())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}