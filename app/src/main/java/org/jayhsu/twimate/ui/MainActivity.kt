package org.jayhsu.twimate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.jayhsu.twimate.TwiMateApplication
import org.jayhsu.twimate.data.local.AppDatabaseHelper
import org.jayhsu.twimate.repository.AppContainer
import org.jayhsu.twimate.repository.AppContainerImpl
import org.jayhsu.twimate.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as TwiMateApplication).container
        setContent {
            TMApp(appContainer)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TMApp(
    appContainer: AppContainer
) {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberAnimatedNavController()
            val appNavigation = remember(navController){ AppNavigation(navController) }
            AppNavGraph(
                navController = navController,
                appNavigation = appNavigation,
                appContainer = appContainer
            )
        }
    }
}

@Preview
@Composable
fun TMAppPreview(){
    AppDatabaseHelper.onCreate(LocalContext.current)
    TMApp(AppContainerImpl())
}