package org.jayhsu.twimate.data.local

import android.net.Uri
import androidx.room.TypeConverter
import org.jayhsu.twimate.data.enums.FileType
import org.jayhsu.twimate.data.enums.TaskStatus

import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromUri(value: String?): Uri? = value?.let { Uri.parse(Uri.decode(it)) }

    @TypeConverter
    fun uriToString(uri: Uri?): String? = uri?.toString()

    @TypeConverter
    fun fromFileType(type: FileType): Int = type.id

    @TypeConverter
    fun toFileType(id: Int): FileType = FileType.values()[id-1]

    @TypeConverter
    fun fromStatus(status: TaskStatus): Int = status.id

    @TypeConverter
    fun toStatus(id: Int): TaskStatus = TaskStatus.values()[id-1]
}