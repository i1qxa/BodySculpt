package com.interaps.BodySculpt.di

import android.app.Application
import com.interaps.BodySculpt.data.local.person.PersonDao
import com.interaps.BodySculpt.data.local.weight_progress.WeightProgressDao
import com.interaps.BodySculpt.ui.persona_iInfo.PersonalInfoFragment
import com.interaps.BodySculpt.ui.weight_progress.WeightProgressFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    ModuleDataBase::class,
])
interface AppComponent {

    val personDao:PersonDao

    val weightProgressDao:WeightProgressDao

    fun inject(fragment: PersonalInfoFragment)

    fun injectWeightProgress(fragment: WeightProgressFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }


}