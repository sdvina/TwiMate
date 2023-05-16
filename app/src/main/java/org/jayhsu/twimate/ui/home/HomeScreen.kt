package org.jayhsu.twimate.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import org.jayhsu.twimate.R
import org.jayhsu.twimate.data.enums.TaskStatus
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import org.jayhsu.twimate.ui.AppBottomBar
import org.jayhsu.twimate.ui.AppBottomNavType
import org.jayhsu.twimate.ui.AppNavigation
import org.jayhsu.twimate.ui.components.MoreActionsButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appNavigation: AppNavigation,
    viewModel: HomeViewModel
) {
    val appBottomNavState = remember { mutableStateOf(AppBottomNavType.HOME) }
    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name))},
                modifier = modifier,
                navigationIcon = { Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)},
                actions = {
                    MoreActionsButton {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.settings)) },
                            onClick = { /*TODO*/ },
                            leadingIcon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = null) }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.about))},
                            onClick = { /*TODO*/ },
                            leadingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = null) }
                        )
                    }
                }
            )
        },
        bottomBar = {
            AppBottomBar(
                appBottomNavState = appBottomNavState
            )
        },
        floatingActionButton = {
            if (appBottomNavState.value == AppBottomNavType.HOME) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Add, "Localized Description") },
                    text = { Text(text = "Extended FAB") },
                    onClick = { /* do something */ },
                    expanded = expandedFab,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        val viewState by viewModel.state.collectAsStateWithLifecycle()
        val unCompleteItems = viewModel.getTaskWithFileFlow(
            TaskStatus.SUSPEND,
            TaskStatus.IN_PROGRESS,
            TaskStatus.FAILURE
        ).collectAsLazyPagingItems()
        val completeItems = viewModel.getTaskWithFileFlow(
            TaskStatus.COMPLETE
        ).collectAsLazyPagingItems()
        when (appBottomNavState.value) {
            AppBottomNavType.HOME -> TaskList(
                modifier = modifier.padding(innerPadding),
                listState = listState,
                taskItems = unCompleteItems
            )
            AppBottomNavType.DOWNLOAD -> TaskList(
                modifier = modifier.padding(innerPadding),
                listState = rememberLazyListState(),
                taskItems = completeItems
            )
        }
    }
}

@Composable
fun TaskList(
    modifier: Modifier,
    listState: LazyListState,
    taskItems: LazyPagingItems<TaskWithFiles>
){
    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        itemsIndexed(taskItems){ _, unCompleteItem ->
            unCompleteItem?.let {
                TaskItemCard(taskItem = it)
            }
        }
    }
}