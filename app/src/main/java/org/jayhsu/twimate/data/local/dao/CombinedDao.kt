package org.jayhsu.twimate.data.local.dao

import androidx.room.Dao
import androidx.room.Transaction

@Dao
interface CombinedDao: TaskDao, FileDao {

    @Transaction
    fun deleteTaskWithFilesByTaskId(vararg taskId: Long){
        deleteFilesByTaskId(*taskId)
        deleteTaskByTaskId(*taskId)
    }
}