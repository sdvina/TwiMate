package org.jayhsu.twimate.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jayhsu.twimate.data.enums.TaskStatus
import java.sql.Date


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id") val taskId: Long = 0,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "status") val status: TaskStatus = TaskStatus.SUSPEND,
    @ColumnInfo(name = "create_date") val createDate: Date,
    )