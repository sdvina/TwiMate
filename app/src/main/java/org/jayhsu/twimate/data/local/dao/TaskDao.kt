package org.jayhsu.twimate.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.jayhsu.twimate.data.local.entity.Task
import org.jayhsu.twimate.data.local.entity.TaskWithFiles

interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTasks(vararg task: Task): List<Long>

    @Query("SELECT * FROM task ORDER BY create_date DESC")
    fun taskPagingSource(): PagingSource<Int, Task>

    @Transaction
    @Query("SELECT * FROM task")
    fun taskWithFilesPagingSource(): PagingSource<Int, TaskWithFiles>

    @Transaction
    @Query("SELECT * FROM task")
    fun getTaskWithFiles(): Flow<List<TaskWithFiles>>

    @Update
    fun updateTasks(vararg task: Task)

    @Delete
    fun deleteTasks(vararg task: Task)

    @Query("DELETE FROM task WHERE task_id IN (:taskId)")
    fun deleteTaskByTaskId(vararg taskId: Long)
}