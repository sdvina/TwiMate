package org.jayhsu.twimate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.jayhsu.twimate.TwiMateApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as TwiMateApplication).container
        setContent {
            TMApp(appContainer)
        }
    }
}