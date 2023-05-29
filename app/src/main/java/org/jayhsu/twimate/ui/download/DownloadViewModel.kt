package org.jayhsu.twimate.ui.download

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.jayhsu.twimate.data.enums.TaskStatus
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import org.jayhsu.twimate.repository.AppContainer
import org.jayhsu.twimate.ui.util.Message

data class DownloadViewState(
    val taskWithFiles: List<TaskWithFiles> = emptyList(),
    val messages: List<Message> = emptyList()
)
class DownloadViewModel(
    appContainer: AppContainer
): ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 20)
    private val muRepository = appContainer.tmRepository
    private var _state = MutableStateFlow(DownloadViewState())
    val state: MutableStateFlow<DownloadViewState>
        get() = _state

    fun getTaskWithFileFlow(vararg status: TaskStatus): Flow<PagingData<TaskWithFiles>> {
        return Pager(pagingConfig) {
            muRepository.taskWithFilesPagingSource()
        }.flow.map{ pagingData ->
            pagingData.filter { taskWithFiles ->
                status.contains(taskWithFiles.task.status)
            }
        }.cachedIn(viewModelScope)
    }

    companion object {
        fun provideFactory(
            appContainer: AppContainer
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DownloadViewModel(appContainer) as T
            }
        }
    }
}