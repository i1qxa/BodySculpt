package com.interaps.BodySculpt.ui.menu_of_day

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.interaps.BodySculpt.data.local.AppDatabase
import java.util.Calendar

class MenuOfDayViewModel(application: Application):AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).menuDao()

    private val year = Calendar.getInstance()[Calendar.YEAR]
    private val month = Calendar.getInstance()[Calendar.MONTH]
    private val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
    private val calendar = Calendar.getInstance()
    private val startTime by lazy { getBeginOfDay() }
    private val endTime by lazy { getEndOfDay() }

    val menuOfDay = dao.getMenuOfDay(startTime, endTime)

    val commonInfo = dao.getTotalNutrientInfo(startTime, endTime)



    fun getBeginOfDay():Long{
        calendar.set(year,month,day,0,0,1)
        return calendar.timeInMillis
    }

    fun getEndOfDay():Long{
        calendar.set(year,month,day,23,58,58)
        return calendar.timeInMillis
    }


}