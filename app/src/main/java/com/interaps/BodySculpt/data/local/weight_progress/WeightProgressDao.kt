package com.interaps.BodySculpt.data.local.weight_progress

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightProgressDao {

    @Query("SELECT * FROM WeightProgressDBEntity")
    suspend fun getWeightProgress():Flow<List<WeightProgressDBEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewWeightProgressValue(value: WeightProgressDBEntity)

}