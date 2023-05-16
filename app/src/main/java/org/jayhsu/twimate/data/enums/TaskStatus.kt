package org.jayhsu.twimate.data.enums

import androidx.annotation.StringRes
import org.jayhsu.twimate.R

enum class TaskStatus(val id: Int, @StringRes val resId: Int){
    SUSPEND(1, R.string.task_status_type_suspend),
    IN_PROGRESS(2, R.string.task_status_type_in_progress),
    FAILURE(3, R.string.task_status_type_failure),
    COMPLETE(4,R.string.task_status_type_complete)
}