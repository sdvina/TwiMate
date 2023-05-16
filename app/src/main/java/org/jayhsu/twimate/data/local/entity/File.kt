package org.jayhsu.twimate.data.local.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jayhsu.twimate.data.enums.FileType
import org.jayhsu.twimate.data.enums.TaskStatus
import java.sql.Date

@Entity(tableName = "file")
data class File(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "file_id") val fileId: Long = 0,
    @ColumnInfo(name = "task_id", index = true) val taskId: Long,
    @ColumnInfo(name = "uri") val uri: Uri,
    @ColumnInfo(name = "type") val type: FileType,
    @ColumnInfo(name = "status") val status: TaskStatus = TaskStatus.SUSPEND,
    @ColumnInfo(name = "create_date") val createDate: Date
)