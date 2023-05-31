package org.jayhsu.twimate.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import org.jayhsu.twimate.R
import org.jayhsu.twimate.data.enums.TaskStatus
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import org.jayhsu.twimate.ui.AppBottomBar
import org.jayhsu.twimate.ui.AppBottomNavType
import org.jayhsu.twimate.ui.AppNavigation
import org.jayhsu.twimate.ui.components.MoreAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appBottomNavState: MutableState<AppBottomNavType>,
    appNavigation: AppNavigation,
    viewModel: HomeViewModel
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name))},
                modifier = modifier,
                navigationIcon = { Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)},
                actions = { MoreAction(appNavigation = appNavigation) }
            )
        },
        bottomBar = {
            AppBottomBar(
                appBottomNavState = appBottomNavState,
                appNavigation = appNavigation
            )
        }
    ) { innerPadding ->
        val unCompleteItems = viewModel.getTaskWithFileFlow(
            TaskStatus.SUSPEND,
            TaskStatus.IN_PROGRESS,
            TaskStatus.FAILURE
        ).collectAsLazyPagingItems()
        TaskList(
            modifier = modifier.padding(innerPadding),
            listState = listState,
            taskItems = unCompleteItems
        )
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