package com.interaps.BodySculpt.ui.weight_progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.databinding.FragmentWeightProgresBinding
import com.interaps.BodySculpt.di.MainApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeightProgressFragment : Fragment() {

    @Inject
    lateinit var viewModel: WeightProgressViewModel

    private var _binding: FragmentWeightProgresBinding? = null
    private val binding: FragmentWeightProgresBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeightProgresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MainApp).appComponent.injectWeightProgress(this)
        observeViewModel()
        setupBtnAddClickListener()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.weightProgress.collect() { weightProgressDB ->
                if (weightProgressDB != null && weightProgressDB.isNotEmpty()) {
                    binding.tvEmptyChartData.visibility = View.GONE
                    binding.chartWeightProgress.visibility = View.VISIBLE
                    val chartModel = AAChartModel()
                    chartModel.apply {
                        chartType(AAChartType.Areaspline)
                        title("Weight change")
                        backgroundColor("#FFFFFF")
                        dataLabelsEnabled(true)
                        series(
                            arrayOf(
                                AASeriesElement()
                                    .name("Date")
                                    .data(weightProgressDB.map { it.value }.toList().toTypedArray())
                            )
                        )
                    }
                    binding.chartWeightProgress.aa_drawChartWithChartModel(chartModel)
                }else{
                    binding.tvEmptyChartData.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun setupBtnAddClickListener() {
        binding.btnAdd.setOnClickListener {
            viewModel.addWeight(binding.etWeight.text.toString().toDouble())
            binding.etWeight.setText("")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}