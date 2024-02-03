package com.interaps.BodySculpt.ui.persona_iInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentPersonalInfoBinding
import com.interaps.BodySculpt.di.MainApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalInfoFragment : Fragment() {

    @Inject lateinit var viewModel: PersonalInfoViewModel

    private var _binding:FragmentPersonalInfoBinding? = null
    private val binding:FragmentPersonalInfoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MainApp).appComponent.inject(this)
        observePersonInfo()
        setupBtnSaveClickListener()
    }

    private fun observePersonInfo(){
        lifecycleScope.launch {
            viewModel.personInfoFlow.collect(){
                binding.etCurrentWeight.setText(it.startWeight.toString())
                binding.etWishWeight.setText(it.wishWeight.toString())
                binding.etHeight.setText(it.height.toString())
            }
        }
    }

    private fun setupBtnSaveClickListener(){
        binding.btnSave.setOnClickListener {
            viewModel.tryToUpdatePersonInfo(
                binding.etCurrentWeight.text.toString().toDouble(),
                binding.etWishWeight.text.toString().toDouble(),
                binding.etHeight.text.toString().toInt()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}