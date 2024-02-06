package com.interaps.BodySculpt.ui.weight_progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDBEntity
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDao
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class WeightProgressViewModel @Inject constructor (private val weightProgressDao: WeightProgressDao):ViewModel() {

    val weightProgress = weightProgressDao.getWeightProgress()

    fun addWeight(weight:Double?){
        if (weight!=null){
            viewModelScope.launch {
                val currentDate = Calendar.getInstance().timeInMillis
                weightProgressDao.addNewWeightProgressValue(WeightProgressDBEntity(
                    0,
                    weight,
                    currentDate
                ))
            }
        }
    }

}