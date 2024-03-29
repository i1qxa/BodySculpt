package com.interaps.BodySculpt.ui.persona_iInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentPersonalInfoBinding
import com.interaps.BodySculpt.di.MainApp
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalInfoFragment : Fragment() {

    @Inject lateinit var viewModel: PersonalInfoViewModel

    private val binding by lazy { FragmentPersonalInfoBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MainApp).appComponent.inject(this)
        observePersonInfo()
        setupBtnSaveClickListener()
        setupSpinner()
    }

    private fun setupSpinner(){
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genders,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerGender.adapter = adapter
        }
    }
    private fun observePersonInfo(){
        lifecycleScope.launch {
            viewModel.personInfoFlow.collect(){ personDBEntity ->
                if (personDBEntity!=null){
                    binding.etCurrentWeight.setText(personDBEntity.startWeight.toString())
                    binding.etWishWeight.setText(personDBEntity.wishWeight.toString())
                    binding.etHeight.setText(personDBEntity.height.toString())
                    binding.spinnerGender.setSelection(personDBEntity.gender)
                }

            }
        }
    }

    private fun setupBtnSaveClickListener(){
        binding.btnSave.setOnClickListener {
            viewModel.tryToUpdatePersonInfo(
                binding.etCurrentWeight.text.toString().toDouble(),
                binding.etWishWeight.text.toString().toDouble(),
                binding.etHeight.text.toString().toInt(),
                binding.spinnerGender.selectedItemPosition
            )
        }
    }


}