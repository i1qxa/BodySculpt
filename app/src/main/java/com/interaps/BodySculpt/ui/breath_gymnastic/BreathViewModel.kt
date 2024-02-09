package com.interaps.BodySculpt.ui.breath_gymnastic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEFAULT_EXERCISE_DURATION: Long = 30000

class BreathViewModel() : ViewModel() {

    val timerLD = MutableLiveData<Long>()
    var timerValue: Long = DEFAULT_EXERCISE_DURATION
    val exerciseInProgress = MutableLiveData<Boolean>(false)

    init {
        timerLD.value = DEFAULT_EXERCISE_DURATION
    }

    private fun launchExercise() {
        exerciseInProgress.value = true
        viewModelScope.launch {
            while (timerValue > 0 && exerciseInProgress.value == true) {
                delay(1000)
                timerValue -= 1000
                timerLD.postValue(timerValue)
            }
            stopExercise()
        }
    }

    fun stopExercise() {
        exerciseInProgress.postValue(false)
        timerValue = DEFAULT_EXERCISE_DURATION
        timerLD.postValue(timerValue)
    }

    fun manageExerciseState() {
        if (exerciseInProgress.value ?: true) {
            stopExercise()
        } else {
            launchExercise()
        }
    }

}