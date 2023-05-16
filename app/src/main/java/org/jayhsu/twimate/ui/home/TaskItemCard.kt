package org.jayhsu.twimate.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jayhsu.twimate.data.local.entity.TaskWithFiles

@Composable
fun TaskItemCard(
    modifier: Modifier = Modifier,
    taskItem: TaskWithFiles
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Text(text = taskItem.task.url)
        Divider()
        taskItem.files.forEach {
            Text(text = it.toString())
        }
    }
}