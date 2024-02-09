package com.interaps.BodySculpt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        val navController = findNavController()
        with(binding) {
            Chart.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_weightProgresFragment)
            }
            Menu.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_menuOfDayFragment)
            }
            BreatheExercise.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_breathGymnasticFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}