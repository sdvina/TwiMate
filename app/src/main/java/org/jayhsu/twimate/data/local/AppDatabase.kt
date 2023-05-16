package org.jayhsu.twimate.data.local


import androidx.room.*
import org.jayhsu.twimate.data.local.dao.CombinedDao
import org.jayhsu.twimate.data.local.entity.File
import org.jayhsu.twimate.data.local.entity.Task

@Database(entities = [Task::class, File::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCombinedDao(): CombinedDao
}