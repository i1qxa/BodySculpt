package com.interaps.BodySculpt.ui.menu_of_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentMenuOfDayBinding
import com.interaps.BodySculpt.databinding.FragmentRecipeListBinding

class MenuOfDayFragment : Fragment() {

    private var _binding: FragmentMenuOfDayBinding? = null
    private val binding: FragmentMenuOfDayBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMenuOfDayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddMenuItem.setOnClickListener {
            findNavController().navigate(R.id.action_menuOfDayFragment_to_recipeListFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}