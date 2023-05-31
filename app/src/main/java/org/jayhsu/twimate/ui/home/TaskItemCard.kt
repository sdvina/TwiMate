package org.jayhsu.twimate.ui.home

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import org.jayhsu.twimate.repository.AppContainerImpl
import org.jayhsu.twimate.ui.components.DownloadButton
import org.jayhsu.twimate.ui.components.DownloadState

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
        LinearProgressIndicator(
            progress = 0.8f
        )
        Divider()
        taskItem.files.forEach { file ->
            Column {
                Row {
                    val buttonState = remember { mutableStateOf(DownloadState.PAUSE) }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .crossfade(true)
                            .data("https://t7.baidu.com/it/u=1956604245,3662848045&fm=193&f=GIF")
                            .size(160, 160)
                            .build(),
                        contentDescription = file.uri.toString()
                    )
                    Text(text = file.uri.toString())
                    DownloadButton(
                        modifier = Modifier.padding(end = 4.dp),
                        action = { /*TODO*/ },
                        downloadState = buttonState
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun TaskItemCardPreview(){
    val appContainer = AppContainerImpl()
    TaskItemCard(
        taskItem = appContainer.fakeRepository.taskWithFiles1
    )
}