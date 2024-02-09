package com.interaps.BodySculpt.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.interaps.BodySculpt.data.local.day_menu.MenuDao
import com.interaps.BodySculpt.data.local.day_menu.MenuItemDBEntity
import com.interaps.BodySculpt.data.local.person.PersonDBEntity
import com.interaps.BodySculpt.data.local.person.PersonDao
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDBEntity
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [
        PersonDBEntity::class,
        WeightProgressDBEntity::class,
        MenuItemDBEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun weightProgress():WeightProgressDao

    abstract fun menuDao():MenuDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "body_sculpt_db"

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            kotlinx.coroutines.internal.synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}