package org.jayhsu.twimate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jayhsu.twimate.data.enums.TaskStatus
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import org.jayhsu.twimate.repository.AppContainer
import org.jayhsu.twimate.ui.util.Message

data class HomeViewState(
    val taskWithFiles: List<TaskWithFiles> = emptyList(),
    val messages: List<Message> = emptyList()
)
class HomeViewModel(
    appContainer: AppContainer
): ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 20)
    private val muRepository = appContainer.tmRepository
    private var _state = MutableStateFlow(HomeViewState())
    val state: MutableStateFlow<HomeViewState>
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
                return HomeViewModel(appContainer) as T
            }
        }
    }
}