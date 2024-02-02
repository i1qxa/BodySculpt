package com.interaps.BodySculpt.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.health.services.client.HealthServices
import androidx.health.services.client.awaitWithException
import androidx.lifecycle.lifecycleScope
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding:FragmentHomeBinding
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
        val healthClient = HealthServices.getClient(requireContext())
        val exerciseClient = healthClient.exerciseClient
        lifecycleScope.launch {
            val capabilities = exerciseClient.getCapabilitiesAsync().awaitWithException()
            if (ExerciseType.RUNNING in capabilities.supportedExerciseTypes) {
                runningCapabilities =
                    capabilities.getExerciseTypeCapabilities(ExerciseType.RUNNING)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}