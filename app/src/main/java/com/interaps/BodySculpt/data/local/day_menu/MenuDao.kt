package com.interaps.BodySculpt.data.local.day_menu

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM MenuItemDBEntity WHERE date > :dayStart AND date < :dayEnd")
    fun getMenuOfDay(dayStart:Long, dayEnd:Long):Flow<List<MenuItemDBEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuItem(item:MenuItemDBEntity)

    @Query("SELECT SUM(calories) as caloriesTotal, SUM(weight) as weightTotal," +
            " SUM(protein) as proteinTotal, SUM(carbs) as carboTotal, SUM(fat) as fatTotal" +
            " FROM MenuItemDBEntity WHERE date> :startDate AND date< :endDate")
    fun getTotalNutrientInfo(startDate:Long, endDate:Long):LiveData<CommonNutrientDataOfDay>

}