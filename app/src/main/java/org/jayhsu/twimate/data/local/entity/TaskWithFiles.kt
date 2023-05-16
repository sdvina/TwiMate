package org.jayhsu.twimate.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithFiles(
    @Embedded val task: Task,
    @Relation(
        entity = File::class,
        parentColumn = "task_id",
        entityColumn = "task_id"
    )
    val files: List<File>
)