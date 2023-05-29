package org.jayhsu.twimate.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.jayhsu.twimate.repository.AppContainer
import org.jayhsu.twimate.ui.download.DownloadScreen
import org.jayhsu.twimate.ui.download.DownloadViewModel
import org.jayhsu.twimate.ui.home.HomeScreen
import org.jayhsu.twimate.ui.home.HomeViewModel
import org.jayhsu.twimate.ui.settings.SettingsScreen

object AppDestinations {
    const val HOME = "home"
    const val DOWNLOAD = "download"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph (
    navController: NavHostController,
    appNavigation: AppNavigation,
    appContainer: AppContainer
) {
    val appBottomNavState = remember { mutableStateOf(AppBottomNavType.HOME) }
    AnimatedNavHost(
        navController = navController,
        startDestination = AppDestinations.HOME,
        modifier = Modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(750))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(750))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(750))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(750))
        }
    ){
        composable(AppDestinations.HOME) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(appContainer)
            )
            HomeScreen(
                appBottomNavState = appBottomNavState,
                appNavigation = appNavigation,
                viewModel = homeViewModel
            )
        }
        composable(AppDestinations.DOWNLOAD) {
            val downloadViewModel: DownloadViewModel = viewModel(
                factory = DownloadViewModel.provideFactory(appContainer)
            )
            DownloadScreen(
                appBottomNavState = appBottomNavState,
                appNavigation = appNavigation,
                viewModel = downloadViewModel
            )
        }
        composable(AppDestinations.SETTINGS){
            SettingsScreen(
                appNavigation = appNavigation
            )
        }
        composable(AppDestinations.ABOUT){
        }
    }
}