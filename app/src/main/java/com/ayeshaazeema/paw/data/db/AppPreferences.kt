package com.ayeshaazeema.paw.data.db

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        prefsEditor = prefs.edit()
    }
    companion object{
        private const val PRIVATE_MODE = 0
        private const val PREF_NAME = "app-prefs"
        private const val IS_FIRST_LAUNCH = "IsFirstLaunch"
    }
    fun setFirstLaunch(isFirstLaunch: Boolean){
        prefsEditor.putBoolean(IS_FIRST_LAUNCH, isFirstLaunch)
        prefsEditor.commit()
    }
    fun isFirstLaunch(): Boolean = prefs.getBoolean(IS_FIRST_LAUNCH, true)
}