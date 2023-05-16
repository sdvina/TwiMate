package org.jayhsu.twimate

import android.app.Application
import org.jayhsu.twimate.data.local.AppDatabaseHelper
import org.jayhsu.twimate.data.local.AppPreferences
import org.jayhsu.twimate.repository.AppContainer
import org.jayhsu.twimate.repository.AppContainerImpl

class TwiMateApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        AppDatabaseHelper.onCreate(this)
        container = AppContainerImpl()
    }
}