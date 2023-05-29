package org.jayhsu.twimate.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.jayhsu.twimate.R
import org.jayhsu.twimate.ui.theme.AppTheme

enum class AppBottomNavType {
    HOME,
    DOWNLOAD
}

@Composable
 fun AppBottomBar(
    modifier: Modifier = Modifier,
    appBottomNavState: MutableState<AppBottomNavType>,
    appNavigation: AppNavigation
 ) {
    BottomAppBar(
        actions = {
            NavigationBar(
                modifier = modifier
            ) {
                NavigationBarItem(
                    selected = appBottomNavState.value == AppBottomNavType.HOME,
                    onClick = {
                        appBottomNavState.value = AppBottomNavType.HOME
                        appNavigation.navigateToHome()
                    },
                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
                    label = { Text(text = stringResource(R.string.home)) }
                )
                NavigationBarItem(
                    selected = appBottomNavState.value == AppBottomNavType.DOWNLOAD,
                    onClick = {
                        appBottomNavState.value = AppBottomNavType.DOWNLOAD
                        appNavigation.navigateToDownload()
                    },
                    icon = { Icon(imageVector = Icons.Filled.Download, contentDescription = null) },
                    label = { Text(text = stringResource(R.string.download)) }
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AppBottomBarPreview(){
    val navController = rememberAnimatedNavController()
    val appNavigation = remember(navController){ AppNavigation(navController) }
    AppTheme() {
        AppBottomBar(
            appBottomNavState = remember { mutableStateOf(AppBottomNavType.HOME) },
            appNavigation = appNavigation
        )
    }
}