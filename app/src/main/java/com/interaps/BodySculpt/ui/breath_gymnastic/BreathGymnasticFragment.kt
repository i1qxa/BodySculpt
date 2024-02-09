package com.interaps.BodySculpt.ui.breath_gymnastic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentBreathGymnasticBinding
import com.interaps.BodySculpt.databinding.FragmentMenuOfDayBinding
import com.interaps.BodySculpt.ui.menu_of_day.MenuOfDayViewModel
import java.text.SimpleDateFormat

class BreathGymnasticFragment : Fragment() {

    private val viewModel by viewModels<BreathViewModel>()

    private var _binding: FragmentBreathGymnasticBinding? = null
    private val binding: FragmentBreathGymnasticBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreathGymnasticBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnClickListener()
        observeExerciseState()
    }

    private fun startAnim() {
        if (viewModel.exerciseInProgress.value==true){
            binding.tvBreathDirection.text = requireContext().getString(R.string.inhale)
            binding.ivProgress.animate().apply {
                duration = 3000
                rotation(1080F)
                scaleX(3F)
                scaleY(3F)
                withEndAction {
                    launchBackAnim()
                }
            }
        }
    }

    private fun launchBackAnim() {
        if (viewModel.exerciseInProgress.value==true) {
            binding.tvBreathDirection.text = requireContext().getString(R.string.exhalation)
            binding.ivProgress.animate().apply {
                duration = 1000
                rotation(360F)
                scaleX(1F)
                scaleY(1F)
                withEndAction {
                    startAnim()
                }
            }
        }
    }

    private fun setupBtnClickListener() {
        binding.btnStartExercise.setOnClickListener {
            viewModel.manageExerciseState()
        }
    }


    private fun observeExerciseState(){
        val formater = SimpleDateFormat("mm:ss")
        viewModel.timerLD.observe(viewLifecycleOwner){
            binding.tvCountDownTimer.text = formater.format(it)
        }

        viewModel.exerciseInProgress.observe(viewLifecycleOwner){
            if (it){
                binding.ivProgress.visibility = View.VISIBLE
                binding.tvBreathDirection.visibility = View.VISIBLE
                startAnim()
                binding.btnStartExercise.text = requireContext().getString(R.string.stop)
            }else{
                binding.ivProgress.visibility = View.INVISIBLE
                binding.tvBreathDirection.visibility = View.INVISIBLE
                binding.btnStartExercise.text = requireContext().getString(R.string.start)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopExercise()
        _binding = null
    }
}