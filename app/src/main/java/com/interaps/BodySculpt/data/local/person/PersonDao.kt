package com.interaps.BodySculpt.data.local.person

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface PersonDao {

    @Query("SELECT * FROM persondbentity LIMIT 1")
    suspend fun getPersonData():PersonDBEntity

    @Query("SELECT * FROM persondbentity LIMIT 1")
    fun getPersonDataFlow(): Flow<PersonDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(person:PersonDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePersonInfo(person: PersonDBEntity)

    @Delete
    suspend fun removePerson(person: PersonDBEntity)
}