package org.jayhsu.twimate.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import org.jayhsu.twimate.data.local.entity.File

interface FileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFiles(vararg file: File)

    @Query("SELECT * FROM file ORDER BY create_date DESC")
    fun filePagingSource(): PagingSource<Int, File>

    @Update
    fun updateFiles(vararg file: File)

    @Delete
    fun deleteFiles(vararg files: File)

    @Query("DELETE FROM file WHERE task_id IN (:taskId)")
    fun deleteFilesByTaskId(vararg taskId: Long)
}