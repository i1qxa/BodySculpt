package com.interaps.BodySculpt.di

import android.app.Application
import android.content.Context

class MainApp:Application() {

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    val Context.appComponent:AppComponent
        get() = when (this){
            is MainApp -> appComponent
            else -> this.applicationContext.appComponent
        }
}