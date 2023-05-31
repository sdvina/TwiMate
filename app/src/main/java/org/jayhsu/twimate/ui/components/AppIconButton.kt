package org.jayhsu.twimate.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.jayhsu.twimate.R


@Composable
fun ShareButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = stringResource(R.string.cd_share)
        )
    }
}

@Composable
fun MoreActionsButton(content:  @Composable (ColumnScope.() -> Unit)) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = stringResource(R.string.cd_more_vert)
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        content = content
    )
}

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    action: () -> Unit,
    downloadState: MutableState<DownloadState>
){
    val iconState = remember { mutableStateOf(Icons.Filled.Pause) }
    IconButton(
        onClick = {
            when (downloadState.value) {
                DownloadState.PAUSE -> {
                    iconState.value = Icons.Filled.PlayArrow
                }
                DownloadState.RESUME -> {
                    iconState.value = Icons.Filled.Pause
                }
                DownloadState.RETRY -> {
                    iconState.value = Icons.Filled.Pause
                }
                DownloadState.COMPLETE -> {
                    iconState.value = Icons.Filled.Check
                }
                DownloadState.FAILURE -> {
                    iconState.value = Icons.Filled.Wallet
                }
            }
            action()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = iconState.value,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun DownloadButtonPreview(){
    DownloadButton(
        action = { /*TODO*/ },
        downloadState = remember { mutableStateOf(DownloadState.COMPLETE) }
    )
}