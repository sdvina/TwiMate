package org.jayhsu.twimate.repository

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import org.jayhsu.twimate.data.local.AppDatabaseHelper
import org.jayhsu.twimate.data.local.entity.File
import org.jayhsu.twimate.data.local.entity.Task
import org.jayhsu.twimate.data.local.entity.TaskWithFiles

class TMRepository {
    private val dao = AppDatabaseHelper.db.getCombinedDao()
    //private val executor = Executors.newSingleThreadExecutor()

    fun addTasks(vararg task: Task) = dao.addTasks(*task)
    fun addFiles(vararg file: File) = dao.addFiles(*file)
    fun taskPagingSource(): PagingSource<Int, Task> =  dao.taskPagingSource()
    fun taskWithFilesPagingSource(): PagingSource<Int, TaskWithFiles> = dao.taskWithFilesPagingSource()
    fun getTaskWithFilesFlow(): Flow<List<TaskWithFiles>> = dao.getTaskWithFiles()
    fun deleteTasksByTaskId(vararg taskId: Long) = dao.deleteTaskByTaskId(*taskId)
    fun deleteFilesByTaskId(vararg taskId: Long) = dao.deleteFilesByTaskId(*taskId)
    fun deleteTaskWithFilesByTaskId(vararg taskId: Long) =  dao.deleteTaskWithFilesByTaskId(*taskId)
}