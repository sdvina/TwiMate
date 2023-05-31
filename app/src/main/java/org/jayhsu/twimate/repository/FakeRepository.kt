package org.jayhsu.twimate.repository

import android.net.Uri
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jayhsu.twimate.data.enums.FileType
import org.jayhsu.twimate.data.enums.TaskStatus
import org.jayhsu.twimate.data.local.entity.File
import org.jayhsu.twimate.data.local.entity.Task
import org.jayhsu.twimate.data.local.entity.TaskWithFiles
import java.sql.Date

class FakeRepository {

    private val task1 = Task(
        taskId = 0,
        url = "url1",
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-12")
    )
    val task2 = Task(
        taskId = 1,
        url = "url2",
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-13")
    )
    val task3 = Task(
        taskId = 2,
        url = "url3",
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-14")
    )
    private val file1 = File(
        fileId = 0,
        taskId = 0,
        uri = Uri.parse(""),
        type = FileType.VIDEO,
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-12")
    )
    private val file2 = File(
        fileId = 0,
        taskId = 0,
        uri = Uri.parse(""),
        type = FileType.VIDEO,
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-12")
    )
    private val file3 = File(
        fileId = 0,
        taskId = 0,
        uri = Uri.parse(""),
        type = FileType.VIDEO,
        status = TaskStatus.SUSPEND,
        createDate = Date.valueOf("2023-05-12")
    )

    val taskWithFiles1 = TaskWithFiles(
        task = task1,
        files = listOf(file1, file2, file3)
    )

    fun addTasks(vararg task: Task) {

    }
    fun addFiles(vararg file: File) {

    }
    fun taskPagingSource(): PagingSource<Int, Task>? {
        return null
    }
    fun taskWithFilesPagingSource(): PagingSource<Int, TaskWithFiles>? {
        return null
    }
    fun getTaskWithFilesFlow(): Flow<List<TaskWithFiles>>? {
        return flow {
            emit(listOf(taskWithFiles1))
        }
    }
    fun deleteTasksByTaskId(vararg taskId: Long) {

    }
    fun deleteFilesByTaskId(vararg taskId: Long) {

    }
    fun deleteTaskWithFilesByTaskId(vararg taskId: Long) {

    }
}