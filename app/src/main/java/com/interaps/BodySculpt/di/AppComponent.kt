package com.interaps.BodySculpt.di

import android.app.Application
import androidx.fragment.app.Fragment
import com.interaps.BodySculpt.data.local.person.PersonDao
import com.interaps.BodySculpt.ui.persona_iInfo.PersonalInfoFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    ModuleDataBase::class,
])
interface AppComponent {

    val personDao:PersonDao

    fun inject(fragment: PersonalInfoFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}