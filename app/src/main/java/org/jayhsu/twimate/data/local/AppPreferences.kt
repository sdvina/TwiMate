package org.jayhsu.twimate.data.local

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private lateinit var prefs: SharedPreferences
    private const val NAME = "AppPreferences"

    private const val DOWNLOAD_PATH = "download_path"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(
        performEdit: (SharedPreferences.Editor) -> Unit
    ) {
        val editor = this.edit()
        performEdit(editor)
        editor.apply()
    }

    var downloadPath: String?
        get() = prefs.getString(DOWNLOAD_PATH, "")
        set(value) = prefs.edit { it.putString(DOWNLOAD_PATH, value) }
}