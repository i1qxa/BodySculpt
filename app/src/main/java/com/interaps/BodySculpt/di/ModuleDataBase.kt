package com.interaps.BodySculpt.di

import android.app.Application
import com.interaps.BodySculpt.data.local.AppDatabase
import com.interaps.BodySculpt.data.local.person.PersonDao
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ModuleDataBase {

    @Provides
    fun provideDB(application: Application):AppDatabase{
        return AppDatabase.getInstance(application)
    }

    @Provides
    fun providePersonDao(appDataBase:AppDatabase):PersonDao{
        return appDataBase.personDao()
    }

    @Provides
    fun provideWeightProgressDao(appDataBase: AppDatabase):WeightProgressDao{
        return appDataBase.weightProgress()
    }

}